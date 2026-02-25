slides:
	marp --allow-local-files --pdf 04_tools.md
	open 04_tools.pdf
	marp --allow-local-files --pdf 04_verilog.md
	open 04_verilog.pdf

clean:
	@rm -rf build generated
	git clean -fd

