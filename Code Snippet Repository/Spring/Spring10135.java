	@Test
	public void shouldWritePartialContentByteRange() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		Resource body = new ClassPathResource("byterangeresource.txt", getClass());
		ResourceRegion region = HttpRange.createByteRange(0, 5).toResourceRegion(body);
		converter.write(region, MediaType.TEXT_PLAIN, outputMessage);

		HttpHeaders headers = outputMessage.getHeaders();
		assertThat(headers.getContentType(), is(MediaType.TEXT_PLAIN));
		assertThat(headers.getContentLength(), is(6L));
		assertThat(headers.get(HttpHeaders.CONTENT_RANGE).size(), is(1));
		assertThat(headers.get(HttpHeaders.CONTENT_RANGE).get(0), is("bytes 0-5/39"));
		assertThat(outputMessage.getBodyAsString(StandardCharsets.UTF_8), is("Spring"));
	}
