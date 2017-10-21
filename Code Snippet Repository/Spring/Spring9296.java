	private static OptionalLong lengthOf(Resource resource) {
		// Don't consume InputStream...
		if (InputStreamResource.class != resource.getClass()) {
			try {
				return OptionalLong.of(resource.contentLength());
			}
			catch (IOException ignored) {
			}
		}
		return OptionalLong.empty();
	}
