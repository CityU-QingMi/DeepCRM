	@Test
	public void requestParams() throws Exception {
		this.request.setMethod("POST");
		this.request.setContentType(FORM_CONTENT_TYPE);
		this.request.setCharacterEncoding(CHARSET);
		this.request.setParameter("first", "value");
		this.request.setParameter("second", "foo", "bar");

		ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(this.request);
		// getting request parameters will consume the request body
		assertFalse(wrapper.getParameterMap().isEmpty());
		assertEquals("first=value&second=foo&second=bar", new String(wrapper.getContentAsByteArray()));
		// SPR-12810 : inputstream body should be consumed
		assertEquals("", new String(FileCopyUtils.copyToByteArray(wrapper.getInputStream())));
	}
