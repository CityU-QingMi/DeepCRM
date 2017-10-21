	@Override
	protected SockJsFrameFormat getFrameFormat(ServerHttpRequest request) {
		// We already validated the parameter above...
		String callback = getCallbackParam(request);

		return new DefaultSockJsFrameFormat("/**/" + callback + "(\"%s\");\r\n") {
			@Override
			protected String preProcessContent(String content) {
				return JavaScriptUtils.javaScriptEscape(content);
			}
		};
	}
