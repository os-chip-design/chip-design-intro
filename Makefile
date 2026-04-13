slides:
	marp --allow-local-files --pdf 10_pdk.md
	open 10_pdk.pdf

clean:
	@rm -rf build generated
	git clean -fd

