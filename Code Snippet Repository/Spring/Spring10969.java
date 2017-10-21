	@Test
	public void parsePathIsOff() throws Exception {
		this.handler.setParsePath(false);
		Map<String, String> vars = new HashMap<>(2);
		vars.put("hotel", "1");
		vars.put("publicpath", "pics/logo.png");
		String template = "http://example.com/hotels/{hotel}/pic/{publicpath}";
		URI actual = this.handler.expand(template, vars);

		assertEquals("http://example.com/hotels/1/pic/pics/logo.png", actual.toString());
	}
