	@Test
	public void inputStreamFormPostRequest() throws Exception {
		this.request.setMethod("POST");
		this.request.setContentType(FORM_CONTENT_TYPE);
		this.request.setCharacterEncoding(CHARSET);
		this.request.setParameter("first", "value");
		this.request.setParameter("second", "foo", "bar");

		ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(this.request);

		byte[] response = FileCopyUtils.copyToByteArray(wrapper.getInputStream());
		assertArrayEquals(response, wrapper.getContentAsByteArray());
	}
