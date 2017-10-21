	@Test
	public void getHeaders() throws Exception {
		HttpHeaders headers = response.getHeaders();
		String headerName = "MyHeader";
		String headerValue1 = "value1";
		headers.add(headerName, headerValue1);
		String headerValue2 = "value2";
		headers.add(headerName, headerValue2);
		headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));

		response.close();
		assertTrue("Header not set", mockResponse.getHeaderNames().contains(headerName));
		List<String> headerValues = mockResponse.getHeaders(headerName);
		assertTrue("Header not set", headerValues.contains(headerValue1));
		assertTrue("Header not set", headerValues.contains(headerValue2));
		assertEquals("Invalid Content-Type", "text/plain;charset=UTF-8", mockResponse.getHeader("Content-Type"));
		assertEquals("Invalid Content-Type", "text/plain;charset=UTF-8", mockResponse.getContentType());
		assertEquals("Invalid Content-Type", "UTF-8", mockResponse.getCharacterEncoding());
	}
