	@Test
	public void strictEncodingOnWithMap() throws Exception {
		this.handler.setStrictEncoding(true);
		Map<String, String> vars = new HashMap<>(2);
		vars.put("userId", "john;doe");
		String template = "http://www.example.com/user/{userId}/dashboard";
		URI actual = this.handler.expand(template, vars);

		assertEquals("http://www.example.com/user/john%3Bdoe/dashboard", actual.toString());
	}
