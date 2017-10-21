	@Test
	public void parsePathIsOn() throws Exception {
		this.handler.setParsePath(true);
		Map<String, String> vars = new HashMap<>(2);
		vars.put("hotel", "1");
		vars.put("publicpath", "pics/logo.png");
		vars.put("scale", "150x150");
		String template = "http://example.com/hotels/{hotel}/pic/{publicpath}/size/{scale}";
		URI actual = this.handler.expand(template, vars);

		assertEquals("http://example.com/hotels/1/pic/pics%2Flogo.png/size/150x150", actual.toString());
	}
