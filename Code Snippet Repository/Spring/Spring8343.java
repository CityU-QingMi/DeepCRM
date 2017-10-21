	protected void printRequest(MockHttpServletRequest request) throws Exception {
		String body = (request.getCharacterEncoding() != null ?
				request.getContentAsString() : MISSING_CHARACTER_ENCODING);

		this.printer.printValue("HTTP Method", request.getMethod());
		this.printer.printValue("Request URI", request.getRequestURI());
		this.printer.printValue("Parameters", getParamsMultiValueMap(request));
		this.printer.printValue("Headers", getRequestHeaders(request));
		this.printer.printValue("Body", body);
		this.printer.printValue("Session Attrs", getSessionAttributes(request));
	}
