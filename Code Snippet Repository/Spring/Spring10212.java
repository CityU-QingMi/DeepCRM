	@Test
	public void readXmlRootElementExternalEntityDisabled() throws Exception {
		Resource external = new ClassPathResource("external.txt", getClass());
		String content =  "<!DOCTYPE root SYSTEM \"http://192.168.28.42/1.jsp\" [" +
				"  <!ELEMENT external ANY >\n" +
				"  <!ENTITY ext SYSTEM \"" + external.getURI() + "\" >]>" +
				"  <rootElement><external>&ext;</external></rootElement>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(content.getBytes("UTF-8"));
		converter.setSupportDtd(true);
		RootElement rootElement = (RootElement) converter.read(RootElement.class, inputMessage);

		assertEquals("", rootElement.external);
	}
