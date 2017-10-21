	protected void printResponse(MockHttpServletResponse response) throws Exception {
		String body = (response.getCharacterEncoding() != null ?
				response.getContentAsString() : MISSING_CHARACTER_ENCODING);

		this.printer.printValue("Status", response.getStatus());
		this.printer.printValue("Error message", response.getErrorMessage());
		this.printer.printValue("Headers", getResponseHeaders(response));
		this.printer.printValue("Content type", response.getContentType());
		this.printer.printValue("Body", body);
		this.printer.printValue("Forwarded URL", response.getForwardedUrl());
		this.printer.printValue("Redirected URL", response.getRedirectedUrl());
		printCookies(response.getCookies());
	}
