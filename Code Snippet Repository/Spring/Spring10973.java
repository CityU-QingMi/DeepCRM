	@Test
	public void strictEncodingAndDefaultUriVariables() throws Exception {
		Map<String, String> defaultVars = new HashMap<>(1);
		defaultVars.put("host", "www.example.com");
		this.handler.setDefaultUriVariables(defaultVars);
		this.handler.setStrictEncoding(true);

		Map<String, Object> vars = new HashMap<>(1);
		vars.put("userId", "john;doe");

		String template = "http://{host}/user/{userId}/dashboard";
		URI actual = this.handler.expand(template, vars);

		assertEquals("http://www.example.com/user/john%3Bdoe/dashboard", actual.toString());
	}
