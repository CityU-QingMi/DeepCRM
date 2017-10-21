	@Test
	public void ignoresLeadingSpacesAndTabs() {
		String s = "    #Ignore this comment\n" +
			"\t\tfoo=bar\n" +
			"\t#Another comment more junk \n" +
			" me=mi\n" +
			"x=x\n" +
			"\n";
		PropertiesEditor pe= new PropertiesEditor();
		pe.setAsText(s);
		Properties p = (Properties) pe.getValue();
		assertTrue("contains 3 entries, not " + p.size(), p.size() == 3);
		assertTrue("foo is bar", p.get("foo").equals("bar"));
		assertTrue("me=mi", p.get("me").equals("mi"));
	}
