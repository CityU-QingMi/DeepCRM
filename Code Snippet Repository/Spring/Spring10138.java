	@Test
	public void applicationOctetStreamDefaultContentType() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		ClassPathResource body = Mockito.mock(ClassPathResource.class);
		BDDMockito.given(body.getFilename()).willReturn("spring.dat");
		BDDMockito.given(body.contentLength()).willReturn(12L);
		BDDMockito.given(body.getInputStream()).willReturn(new ByteArrayInputStream("Spring Framework".getBytes()));
		HttpRange range = HttpRange.createByteRange(0, 5);
		ResourceRegion resourceRegion = range.toResourceRegion(body);

		converter.write(Collections.singletonList(resourceRegion), null, outputMessage);

		assertThat(outputMessage.getHeaders().getContentType(), is(MediaType.APPLICATION_OCTET_STREAM));
		assertThat(outputMessage.getHeaders().getFirst(HttpHeaders.CONTENT_RANGE), is("bytes 0-5/12"));
		assertThat(outputMessage.getBodyAsString(StandardCharsets.UTF_8), is("Spring"));
	}
