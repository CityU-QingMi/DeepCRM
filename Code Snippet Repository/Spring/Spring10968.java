	@Test
	public void defaultUriVariables() throws Exception {
		Map<String, String> defaultVars = new HashMap<>(2);
		defaultVars.put("host", "api.example.com");
		defaultVars.put("port", "443");
		this.handler.setDefaultUriVariables(defaultVars);

		Map<String, Object> vars = new HashMap<>(1);
		vars.put("id", 123L);

		String template = "https://{host}:{port}/v42/customers/{id}";
		URI actual = this.handler.expand(template, vars);

		assertEquals("https://api.example.com:443/v42/customers/123", actual.toString());
	}
