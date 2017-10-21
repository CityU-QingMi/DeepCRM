	@Test
	@SuppressWarnings("")
	public void readXmlTypeSet() throws Exception {
		String content = "<set><foo s=\"1\"/><bar s=\"2\"/></set>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(content.getBytes("UTF-8"));
		Set<TestType> result = (Set<TestType>) converter.read(typeSetType, null, inputMessage);

		assertEquals("Invalid result", 2, result.size());
		assertTrue("Invalid result", result.contains(new TestType("1")));
		assertTrue("Invalid result", result.contains(new TestType("2")));
	}
