	@Test
	@SuppressWarnings("")
	public void readXmlTypeList() throws Exception {
		String content = "<list><foo s=\"1\"/><bar s=\"2\"/></list>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(content.getBytes("UTF-8"));
		List<TestType> result = (List<TestType>) converter.read(typeListType, null, inputMessage);

		assertEquals("Invalid result", 2, result.size());
		assertEquals("Invalid result", "1", result.get(0).s);
		assertEquals("Invalid result", "2", result.get(1).s);
	}
