import chisel3._
import chisel3.util._
import _root_.circt.stage.ChiselStage

import chisel3._
import chisel3.util._

class VgaSyncGenerator extends Module {
	val io = IO(new Bundle {
		val hsync = Output(Bool())
		val vsync = Output(Bool())
		val display_on = Output(Bool())
		val hpos = Output(UInt(10.W))
		val vpos = Output(UInt(10.W))
	})
	
	// VGA timing parameters (for 640x480 @ 60Hz, standard VGA)
	val hRes = 640.U         // Horizontal resolution (pixels)
	val hSyncStart = 656.U   // Start of horizontal sync pulse
	val hSyncEnd = 752.U     // End of horizontal sync pulse
	val hTotal = 800.U       // Total horizontal pixels (including blanking)
	
	val vRes = 480.U         // Vertical resolution (lines)
	val vSyncStart = 490.U   // Start of vertical sync pulse
	val vSyncEnd = 492.U     // End of vertical sync pulse
	val vTotal = 525.U       // Total vertical lines (including blanking)
	
	// Registers for horizontal and vertical counters
	val hCounter = RegInit(0.U(10.W)) // Horizontal pixel counter
	val vCounter = RegInit(0.U(10.W)) // Vertical line counter
	
	// Horizontal counter logic
	when(hCounter === hTotal - 1.U) {
		hCounter := 0.U
		when(vCounter === vTotal - 1.U) {
			vCounter := 0.U
		}.otherwise {
			vCounter := vCounter + 1.U
		}
	}.otherwise {
		hCounter := hCounter + 1.U
	}
	
	// Generate sync signals
	io.hsync := !(hCounter >= hSyncStart && hCounter < hSyncEnd)
	io.vsync := !(vCounter >= vSyncStart && vCounter < vSyncEnd)
	
	// Determine if we're in the visible display area
	val visibleArea = (hCounter < hRes) && (vCounter < vRes)
	io.display_on := visibleArea
	
	// Output current pixel coordinates
	io.hpos := hCounter; //Mux(visibleArea, hCounter, 0.U)
	io.vpos := vCounter; //Mux(visibleArea, vCounter, 0.U)
}
