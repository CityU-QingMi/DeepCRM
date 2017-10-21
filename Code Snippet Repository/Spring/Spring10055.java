	@Test
	public void writeForm() {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.set("name 1", "value 1");
		body.add("name 2", "value 2+1");
		body.add("name 2", "value 2+2");
		body.add("name 3", null);
		MockServerHttpResponse response = new MockServerHttpResponse();
		this.writer.write(Mono.just(body), null, MediaType.APPLICATION_FORM_URLENCODED, response, null).block();

		String responseBody = response.getBodyAsString().block();
		assertEquals("name+1=value+1&name+2=value+2%2B1&name+2=value+2%2B2&name+3", responseBody);
		assertEquals(MediaType.APPLICATION_FORM_URLENCODED, response.getHeaders().getContentType());
		assertEquals(responseBody.getBytes().length, response.getHeaders().getContentLength());
	}
