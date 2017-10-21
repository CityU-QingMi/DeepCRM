	@Test
	public void defaultUriVarsSpr14147() throws Exception {
		Map<String, String> defaultUriVars = new HashMap<>(2);
		defaultUriVars.put("host", "api.example.com");
		defaultUriVars.put("port", "443");
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
		factory.setDefaultUriVariables(defaultUriVars);

		URI uri = factory.expand("https://{host}:{port}/v42/customers/{id}", singletonMap("id", 123L));
		assertEquals("https://api.example.com:443/v42/customers/123", uri.toString());
	}
