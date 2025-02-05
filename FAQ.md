## Openlane2 Installation

### *It takes a long time build!*

This is intended behaviour as the Openlane2 builds from scratch - using your computer to compile the entire program.

### False-Alarm Deprecated issues
```zsh
nix-shell --pure ~/openlane2/shell.nix
```
#### Workaround
Add this setting onto the command.
```
--extra-deprecated-features url-literals
```

### Openlane2 Rate Limit

Downloading the sanity check for the Openlane2's sky130 PDK command of this produces this error
```rust
[nix-shell:~/openlane2]$ openlane --log-level ERROR --condensed --show-progress-bar --smoke-test
Version 0fe599b2afb6708d281543108caf8310912f54af not found locally, attempting to download…
Traceback (most recent call last):
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/manage.py", line 181, in fetch
release_link_list = version_object.get_release_links(
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/common.py", line 192, in get_release_links
release = github.get_release_links(f"{self.pdk}-{self.name}", session)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/github.py", line 177, in get_release_links
return session.api(volare_repo, f"/releases/tags/{release}", "get")
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/github.py", line 140, in api
req.raise_for_status()
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/httpx/_models.py", line 761, in raise_for_status
raise HTTPStatusError(message, request=request, response=self)
httpx.HTTPStatusError: Client error '403 rate limit exceeded' for url 'https://api.github.com/repos/efabless/volare/releases/tags/sky130-0fe599b2afb6708d281543108caf8310912f54af'
For more information check: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/403
```
#### Workaround
Wait for some minutes and retry again! It just means that people are overloading the GitHub API.