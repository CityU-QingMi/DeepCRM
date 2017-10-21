	@Test
	public void partialContentMultipleByteRanges() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		Resource body = new ClassPathResource("byterangeresource.txt", getClass());
		List<HttpRange> rangeList = HttpRange.parseRanges("bytes=0-5,7-15,17-20,22-38");
		List<ResourceRegion> regions = new ArrayList<>();
		for(HttpRange range : rangeList) {
			regions.add(range.toResourceRegion(body));
		}

		converter.write(regions, MediaType.TEXT_PLAIN, outputMessage);

		HttpHeaders headers = outputMessage.getHeaders();
		assertThat(headers.getContentType().toString(), Matchers.startsWith("multipart/byteranges;boundary="));
		String boundary = "--" + headers.getContentType().toString().substring(30);
		String content = outputMessage.getBodyAsString(StandardCharsets.UTF_8);
		String[] ranges = StringUtils.tokenizeToStringArray(content, "\r\n", false, true);

		assertThat(ranges[0], is(boundary));
		assertThat(ranges[1], is("Content-Type: text/plain"));
		assertThat(ranges[2], is("Content-Range: bytes 0-5/39"));
		assertThat(ranges[3], is("Spring"));

		assertThat(ranges[4], is(boundary));
		assertThat(ranges[5], is("Content-Type: text/plain"));
		assertThat(ranges[6], is("Content-Range: bytes 7-15/39"));
		assertThat(ranges[7], is("Framework"));

		assertThat(ranges[8], is(boundary));
		assertThat(ranges[9], is("Content-Type: text/plain"));
		assertThat(ranges[10], is("Content-Range: bytes 17-20/39"));
		assertThat(ranges[11], is("test"));

		assertThat(ranges[12], is(boundary));
		assertThat(ranges[13], is("Content-Type: text/plain"));
		assertThat(ranges[14], is("Content-Range: bytes 22-38/39"));
		assertThat(ranges[15], is("resource content."));
	}
