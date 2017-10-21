	@Test
	public void useHttpOutputMessageMediaType() throws Exception {

		MediaType outputMessageMediaType = MediaType.TEXT_HTML;
		this.response.getHeaders().setContentType(outputMessageMediaType);

		HttpMessageWriter<String> writer = getWriter(TEXT_PLAIN_UTF_8, TEXT_HTML);
		writer.write(Mono.just("body"), forClass(String.class), TEXT_PLAIN, this.response, NO_HINTS);

		assertEquals(outputMessageMediaType, this.response.getHeaders().getContentType());
		assertEquals(outputMessageMediaType, this.mediaTypeCaptor.getValue());
	}
