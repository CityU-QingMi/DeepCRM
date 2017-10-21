	@Test
	public void cachedContentWithOverflow() throws Exception {
		this.request.setMethod("GET");
		this.request.setCharacterEncoding(CHARSET);
		this.request.setContent("Hello World".getBytes(CHARSET));

		ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(this.request, 3) {
			@Override
			protected void handleContentOverflow(int contentCacheLimit) {
				throw new IllegalStateException(String.valueOf(contentCacheLimit));
			}
		};

		try {
			FileCopyUtils.copyToByteArray(wrapper.getInputStream());
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertEquals("3", ex.getMessage());
		}
	}
