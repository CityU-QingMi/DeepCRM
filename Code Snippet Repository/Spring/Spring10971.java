	@Test
	public void strictEncodingIsOffWithMap() throws Exception {
		this.handler.setStrictEncoding(false);
		Map<String, String> vars = new HashMap<>(2);
		vars.put("userId", "john;doe");
		String template = "http://www.example.com/user/{userId}/dashboard";
		URI actual = this.handler.expand(template, vars);

		assertEquals("http://www.example.com/user/john;doe/dashboard", actual.toString());
	}
