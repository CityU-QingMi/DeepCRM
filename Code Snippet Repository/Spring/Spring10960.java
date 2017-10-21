	@Test
	public void cachedContentWithLimit() throws Exception {
		this.request.setMethod("GET");
		this.request.setCharacterEncoding(CHARSET);
		this.request.setContent("Hello World".getBytes(CHARSET));

		ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(this.request, 3);
		byte[] response = FileCopyUtils.copyToByteArray(wrapper.getInputStream());
		assertArrayEquals("Hello World".getBytes(CHARSET), response);
		assertArrayEquals("Hel".getBytes(CHARSET), wrapper.getContentAsByteArray());
	}
