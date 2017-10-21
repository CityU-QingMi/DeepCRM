	@Override
	protected Message readInternal(Class<? extends Message> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		MediaType contentType = inputMessage.getHeaders().getContentType();
		if (contentType == null) {
			contentType = PROTOBUF;
		}
		Charset charset = contentType.getCharset();
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}

		try {
			Message.Builder builder = getMessageBuilder(clazz);
			if (PROTOBUF.isCompatibleWith(contentType)) {
				builder.mergeFrom(inputMessage.getBody(), this.extensionRegistry);
			}
			else if (TEXT_PLAIN.isCompatibleWith(contentType)) {
				InputStreamReader reader = new InputStreamReader(inputMessage.getBody(), charset);
				TextFormat.merge(reader, this.extensionRegistry, builder);
			}
			else if (this.protobufFormatSupport != null) {
				this.protobufFormatSupport.merge(inputMessage.getBody(), charset, contentType,
						this.extensionRegistry, builder);
			}
			return builder.build();
		}
		catch (Exception ex) {
			throw new HttpMessageNotReadableException("Could not read Protobuf message: " + ex.getMessage(), ex);
		}
	}
