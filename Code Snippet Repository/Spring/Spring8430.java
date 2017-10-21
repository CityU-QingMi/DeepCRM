	@Test
	public void testSpr5906() throws Exception {
		// verify the property values have been evaluated as expressions
		assertEquals("Dave", props.getProperty("user.name"));
		assertEquals("Andy", props.getProperty("username"));

		// verify the property keys have been evaluated as expressions
		assertEquals("exists", props.getProperty("Dave"));
		assertEquals("exists also", props.getProperty("Andy"));
	}
