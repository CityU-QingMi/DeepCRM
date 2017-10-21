	@Test
	public void standardMultipartResolverWithEncodedFileName() throws Exception {
		byte[] boundary = MimeTypeUtils.generateMultipartBoundary();
		String boundaryText = new String(boundary, "US-ASCII");
		Map<String, String> params = Collections.singletonMap("boundary", boundaryText);

		String content =
				"--" + boundaryText + "\n" +
				"Content-Disposition: form-data; name=\"file\"; filename*=\"utf-8''%C3%A9l%C3%A8ve.txt\"\n" +
				"Content-Type: text/plain\n" +
				"Content-Length: 7\n" +
				"\n" +
				"content\n" +
				"--" + boundaryText + "--";

		RequestEntity<byte[]> requestEntity =
				RequestEntity.post(new URI(baseUrl + "/standard-resolver/spr13319"))
						.contentType(new MediaType(MediaType.MULTIPART_FORM_DATA, params))
						.body(content.getBytes(StandardCharsets.US_ASCII));

		ByteArrayHttpMessageConverter converter = new ByteArrayHttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.MULTIPART_FORM_DATA));
		this.restTemplate.setMessageConverters(Collections.singletonList(converter));

		ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
