slides:
	marp --allow-local-files --pdf tutorial.md
	open tutorial.pdf

clean:
	@rm -rf build generated
	git clean -fd

