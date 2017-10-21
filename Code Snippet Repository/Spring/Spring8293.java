	public WebTestClient.ResponseSpec valueMatches(String name, String pattern) {
		String value = getHeaders().getFirst(name);
		if (value == null) {
			fail(getMessage(name) + " not found");
		}
		boolean match = Pattern.compile(pattern).matcher(value).matches();
		String message = getMessage(name) + "=\'" + value + "\' does not match \'" + pattern + "\'";
		this.exchangeResult.assertWithDiagnostics(() -> assertTrue(message, match));
		return this.responseSpec;
	}
