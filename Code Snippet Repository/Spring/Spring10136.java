	@Test
	public void shouldWritePartialContentByteRangeNoEnd() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		Resource body = new ClassPathResource("byterangeresource.txt", getClass());
		ResourceRegion region = HttpRange.createByteRange(7).toResourceRegion(body);
		converter.write(region, MediaType.TEXT_PLAIN, outputMessage);

		HttpHeaders headers = outputMessage.getHeaders();
		assertThat(headers.getContentType(), is(MediaType.TEXT_PLAIN));
		assertThat(headers.getContentLength(), is(32L));
		assertThat(headers.get(HttpHeaders.CONTENT_RANGE).size(), is(1));
		assertThat(headers.get(HttpHeaders.CONTENT_RANGE).get(0), is("bytes 7-38/39"));
		assertThat(outputMessage.getBodyAsString(StandardCharsets.UTF_8), is("Framework test resource content."));
	}
