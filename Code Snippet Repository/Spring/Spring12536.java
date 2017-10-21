	@Test
	@SuppressWarnings("")
	public void withoutMessageSource() throws Exception {
		MockServletContext sc = new MockServletContext("");
		XmlWebApplicationContext wac = new XmlWebApplicationContext();
		wac.setParent(root);
		wac.setServletContext(sc);
		wac.setNamespace("testNamespace");
		wac.setConfigLocations(new String[] {"/org/springframework/web/context/WEB-INF/test-servlet.xml"});
		wac.refresh();
		try {
			wac.getMessage("someMessage", null, Locale.getDefault());
			fail("Should have thrown NoSuchMessageException");
		}
		catch (NoSuchMessageException ex) {
			// expected;
		}
		String msg = wac.getMessage("someMessage", null, "default", Locale.getDefault());
		assertTrue("Default message returned", "default".equals(msg));
	}
