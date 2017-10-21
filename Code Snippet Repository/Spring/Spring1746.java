	@Test
	public void twoProperties() {
		String s = "foo=bar with whitespace\n" +
			"me=mi";
		PropertiesEditor pe= new PropertiesEditor();
		pe.setAsText(s);
		Properties p = (Properties) pe.getValue();
		assertTrue("contains two entries", p.entrySet().size() == 2);
		assertTrue("foo=bar with whitespace", p.get("foo").equals("bar with whitespace"));
		assertTrue("me=mi", p.get("me").equals("mi"));
	}
