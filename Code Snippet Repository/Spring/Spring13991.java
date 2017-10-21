	@Override
	@Nullable
	protected String[] readMessages(ServerHttpRequest request) throws IOException {
		SockJsMessageCodec messageCodec = getServiceConfig().getMessageCodec();
		MediaType contentType = request.getHeaders().getContentType();
		if (contentType != null && MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)) {
			MultiValueMap<String, String> map = this.formConverter.read(null, request);
			String d = map.getFirst("d");
			return (StringUtils.hasText(d) ? messageCodec.decode(d) : null);
		}
		else {
			return messageCodec.decodeInputStream(request.getBody());
		}
	}
