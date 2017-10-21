	@Test
	public void wrapFormEncodedOnly() throws Exception {
		request.setContent("".getBytes("ISO-8859-1"));
		String[] contentTypes = new String[] {"text/plain", "multipart/form-data"};
		for (String contentType : contentTypes) {
			request.setContentType(contentType);
			filterChain = new MockFilterChain();
			filter.doFilter(request, response, filterChain);
			assertSame("Should not wrap for content type " + contentType, request, filterChain.getRequest());
		}
	}
