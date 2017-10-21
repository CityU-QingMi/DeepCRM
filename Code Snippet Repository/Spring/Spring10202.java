	@Test
	@SuppressWarnings("")
	public void readXmlRootElementList() throws Exception {
		String content = "<list><rootElement><type s=\"1\"/></rootElement><rootElement><type s=\"2\"/></rootElement></list>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(content.getBytes("UTF-8"));
		List<RootElement> result = (List<RootElement>) converter.read(rootElementListType, null, inputMessage);

		assertEquals("Invalid result", 2, result.size());
		assertEquals("Invalid result", "1", result.get(0).type.s);
		assertEquals("Invalid result", "2", result.get(1).type.s);
	}
