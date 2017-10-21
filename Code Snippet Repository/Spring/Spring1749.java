	@Test
	public void handlesEmptyPropertyWithoutEquals() {
		String s = "foo\nme=mi\nx=x";
		PropertiesEditor pe= new PropertiesEditor();
		pe.setAsText(s);
		Properties p = (Properties) pe.getValue();
		assertTrue("contains three entries", p.entrySet().size() == 3);
		assertTrue("foo is empty", p.get("foo").equals(""));
		assertTrue("me=mi", p.get("me").equals("mi"));
	}
