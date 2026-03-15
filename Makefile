slides:
	marp --allow-local-files --pdf 07_floorplanning.md
	open 07_floorplanning.pdf

clean:
	@rm -rf build generated
	git clean -fd

