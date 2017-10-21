	@Test
	public void writeMultipleRegions() throws Exception {

		testWrite(get("/").range(of(0,5), of(7,15), of(17,20), of(22,38)).build());

		HttpHeaders headers = this.response.getHeaders();
		String contentType = headers.getContentType().toString();
		String boundary = contentType.substring(30);

		assertThat(contentType, startsWith("multipart/byteranges;boundary="));

		StepVerifier.create(this.response.getBodyAsString())
				.consumeNextWith(content -> {
					String[] actualRanges = StringUtils.tokenizeToStringArray(content, "\r\n", false, true);
					String[] expected = new String[] {
							"--" + boundary,
							"Content-Type: text/plain",
							"Content-Range: bytes 0-5/39",
							"Spring",
							"--" + boundary,
							"Content-Type: text/plain",
							"Content-Range: bytes 7-15/39",
							"Framework",
							"--" + boundary,
							"Content-Type: text/plain",
							"Content-Range: bytes 17-20/39",
							"test",
							"--" + boundary,
							"Content-Type: text/plain",
							"Content-Range: bytes 22-38/39",
							"resource content.",
							"--" + boundary + "--"
					};
					assertArrayEquals(expected, actualRanges);
				})
				.expectComplete()
				.verify();
	}
