	protected String extractLocalFilePath(URL url) {
		final String filePart = url.getFile();
		if ( filePart != null && filePart.indexOf( ' ' ) != -1 ) {
			//unescaped (from the container), keep as is
			return filePart;
		}
		else {
			try {
				return url.toURI().getSchemeSpecificPart();
			}
			catch (URISyntaxException e) {
				throw new IllegalArgumentException(
						"Unable to visit JAR " + url + ". Cause: " + e.getMessage(), e
				);
			}
		}
	}
