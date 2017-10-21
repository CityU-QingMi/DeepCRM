	@Test
	public void getUriWithEncoding() throws Exception {
        URI uri = new URI("https://example.com/%E4%B8%AD%E6%96%87" +
				"?redirect=https%3A%2F%2Fgithub.com%2Fspring-projects%2Fspring-framework");
        mockRequest.setScheme(uri.getScheme());
        mockRequest.setServerName(uri.getHost());
        mockRequest.setServerPort(uri.getPort());
        mockRequest.setRequestURI(uri.getRawPath());
        mockRequest.setQueryString(uri.getRawQuery());
        assertEquals("Invalid uri", uri, request.getURI());
    }
