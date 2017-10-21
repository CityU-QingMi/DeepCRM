	@Test
	public void setPropertiesProperty() throws Exception {
		PropsTester target = new PropsTester();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setPropertyValue("name", "ptest");

		// Note format...
		String ps = "peace=war\nfreedom=slavery";
		accessor.setPropertyValue("properties", ps);

		assertTrue("name was set", target.name.equals("ptest"));
		assertTrue("properties non null", target.properties != null);
		String freedomVal = target.properties.getProperty("freedom");
		String peaceVal = target.properties.getProperty("peace");
		assertTrue("peace==war", peaceVal.equals("war"));
		assertTrue("Freedom==slavery", freedomVal.equals("slavery"));
	}
