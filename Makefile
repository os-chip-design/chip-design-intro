slides:
	marp --allow-local-files --pdf 06_project_start.md
	open 06_project_start.pdf

clean:
	@rm -rf build generated
	git clean -fd

