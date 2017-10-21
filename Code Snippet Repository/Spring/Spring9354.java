	private void writeMultipart(final MultiValueMap<String, Object> parts, HttpOutputMessage outputMessage) throws IOException {
		final byte[] boundary = generateMultipartBoundary();
		Map<String, String> parameters = new HashMap<>(2);
		parameters.put("boundary", new String(boundary, "US-ASCII"));
		if (!isFilenameCharsetSet()) {
			parameters.put("charset", this.charset.name());
		}

		MediaType contentType = new MediaType(MediaType.MULTIPART_FORM_DATA, parameters);
		HttpHeaders headers = outputMessage.getHeaders();
		headers.setContentType(contentType);

		if (outputMessage instanceof StreamingHttpOutputMessage) {
			StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage) outputMessage;
			streamingOutputMessage.setBody(new StreamingHttpOutputMessage.Body() {
				@Override
				public void writeTo(OutputStream outputStream) throws IOException {
					writeParts(outputStream, parts, boundary);
					writeEnd(outputStream, boundary);
				}
			});
		}
		else {
			writeParts(outputMessage.getBody(), parts, boundary);
			writeEnd(outputMessage.getBody(), boundary);
		}
	}
