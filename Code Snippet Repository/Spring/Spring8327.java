	public ResultMatcher contentTypeCompatibleWith(final MediaType contentType) {
		return result -> {
			String actual = result.getResponse().getContentType();
			assertTrue("Content type not set", actual != null);
			if (actual != null) {
				MediaType actualContentType = MediaType.parseMediaType(actual);
				assertTrue("Content type [" + actual + "] is not compatible with [" + contentType + "]",
						actualContentType.isCompatibleWith(contentType));
			}
		};
	}
