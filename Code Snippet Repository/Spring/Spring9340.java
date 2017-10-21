	@Override
	public void write(final BufferedImage image, @Nullable final MediaType contentType,
			final HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		final MediaType selectedContentType = getContentType(contentType);
		outputMessage.getHeaders().setContentType(selectedContentType);

		if (outputMessage instanceof StreamingHttpOutputMessage) {
			StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage) outputMessage;
			streamingOutputMessage.setBody(new StreamingHttpOutputMessage.Body() {
				@Override
				public void writeTo(OutputStream outputStream) throws IOException {
					writeInternal(image, selectedContentType, outputStream);
				}
			});
		}
		else {
			writeInternal(image, selectedContentType, outputMessage.getBody());
		}
	}
