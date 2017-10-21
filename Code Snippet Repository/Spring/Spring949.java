	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		boolean nioPathCandidate = !text.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX);
		if (nioPathCandidate && !text.startsWith("/")) {
			try {
				URI uri = new URI(text);
				if (uri.getScheme() != null) {
					nioPathCandidate = false;
					// Let's try NIO file system providers via Paths.get(URI)
					setValue(Paths.get(uri).normalize());
					return;
				}
			}
			catch (URISyntaxException ex) {
				// Not a valid URI: Let's try as Spring resource location.
			}
			catch (FileSystemNotFoundException ex) {
				// URI scheme not registered for NIO:
				// Let's try URL protocol handlers via Spring's resource mechanism.
			}
		}

		this.resourceEditor.setAsText(text);
		Resource resource = (Resource) this.resourceEditor.getValue();
		if (resource == null) {
			setValue(null);
		}
		else if (!resource.exists() && nioPathCandidate) {
			setValue(Paths.get(text).normalize());
		}
		else {
			try {
				setValue(resource.getFile().toPath());
			}
			catch (IOException ex) {
				throw new IllegalArgumentException("Failed to retrieve file for " + resource, ex);
			}
		}
	}
