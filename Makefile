slides:
	marp --allow-local-files --pdf 03_verilog.md
	open 03_verilog.pdf

clean:
	@rm -rf build generated
	git clean -fd

