import chisel3._
import chisel3.simulator.EphemeralSimulator._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import scala.util.Random

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class DisplayTester extends AnyFreeSpec with Matchers {
	"Displayer should Display" in {
		simulate(new MandlebrotDisplayer(Constants.bit_width, Constants.fractional_bits, Constants.max_itterations)) { dut =>
			
			dut.reset.poke(true.B);
			dut.clock.step(1);
			dut.reset.poke(false.B);
			
			// Image dimensions
			val width = 640;
			val height = 480;
			
			// Create an image data array
			val image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
			
			dut.clock.step(1);
			
			var x_pix = 0;
			var y_pix = 0;
			
			val clocks_per_pixel = 1;
			
			for (i <- 0 until (1.3 * width.toFloat * height.toFloat).toInt) {
				val hsync = dut.io.hsync.peek().litToBoolean;
				val vsync = dut.io.vsync.peek().litToBoolean;
				val R = dut.io.R.peek().litValue.toInt << 6;
				val G = dut.io.G.peek().litValue.toInt << 6;
				val B = dut.io.B.peek().litValue.toInt << 6;
				val color = R << 16 | G << 8 | B;
				
				if (x_pix < width && y_pix < height) {
					image.setRGB(x_pix, y_pix, color);
				}
				
				x_pix += 1;
				
				if (!hsync) {
					
					// Move to next line on h-sync pulse
					while (!dut.io.hsync.peek().litToBoolean) {
						dut.clock.step(1);
					}
					
					dut.clock.step(48 - 1); //This is am impicit wait periode called the "Back Porch".
					
					x_pix = 0
					y_pix += 1
				}
				if (!vsync) {
					
					// Reset to the top-left corner on v-sync pulse
					while (!dut.io.vsync.peek().litToBoolean) {
						dut.clock.step(1);
					}
					
					dut.clock.step(33 * 800); //This is am impicit wait periode called the "Back Porch".
					
					x_pix = 1;
					y_pix = 0;
				}
				if (y_pix >= 0 && y_pix < height && x_pix < width) {
					// Write the current pixel's RGB values to the image
					image.setRGB(x_pix, y_pix, color)
				}
				
				dut.clock.step(clocks_per_pixel);				
			}
			
			println("Saving image")
			// Save the image to disk
			val outputFile = new File("mandelbrot_display_pos.png")
			ImageIO.write(image, "png", outputFile)
			
		}
	}
}
