	@Test
	@SuppressWarnings("")
	public void readXmlRootElementSet() throws Exception {
		String content = "<set><rootElement><type s=\"1\"/></rootElement><rootElement><type s=\"2\"/></rootElement></set>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(content.getBytes("UTF-8"));
		Set<RootElement> result = (Set<RootElement>) converter.read(rootElementSetType, null, inputMessage);

		assertEquals("Invalid result", 2, result.size());
		assertTrue("Invalid result", result.contains(new RootElement("1")));
		assertTrue("Invalid result", result.contains(new RootElement("2")));
	}
