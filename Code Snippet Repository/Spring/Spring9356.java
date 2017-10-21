	@Override
	protected Resource readInternal(Class<? extends Resource> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		if (this.supportsReadStreaming && InputStreamResource.class == clazz) {
			return new InputStreamResource(inputMessage.getBody()) {
				@Override
				public String getFilename() {
					return inputMessage.getHeaders().getContentDisposition().getFilename();
				}
			};
		}
		else if (clazz.isAssignableFrom(ByteArrayResource.class)) {
			byte[] body = StreamUtils.copyToByteArray(inputMessage.getBody());
			return new ByteArrayResource(body) {
				@Override
				@Nullable
				public String getFilename() {
					return inputMessage.getHeaders().getContentDisposition().getFilename();
				}
			};
		}
		else {
			throw new IllegalStateException("Unsupported resource class: " + clazz);
		}
	}
