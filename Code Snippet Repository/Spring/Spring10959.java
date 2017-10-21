	@Test
	public void cachedContent() throws Exception {
		this.request.setMethod("GET");
		this.request.setCharacterEncoding(CHARSET);
		this.request.setContent("Hello World".getBytes(CHARSET));

		ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(this.request);
		byte[] response = FileCopyUtils.copyToByteArray(wrapper.getInputStream());
		assertArrayEquals(response, wrapper.getContentAsByteArray());
	}
