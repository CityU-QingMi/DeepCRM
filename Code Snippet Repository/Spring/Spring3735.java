	@Test
	public void testOperationParameterMetadata() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();
		ModelMBeanOperationInfo oper = info.getOperation("add");
		MBeanParameterInfo[] params = oper.getSignature();

		assertEquals("Invalid number of params", 2, params.length);
		assertEquals("Incorrect name for x param", "x", params[0].getName());
		assertEquals("Incorrect type for x param", int.class.getName(), params[0].getType());

		assertEquals("Incorrect name for y param", "y", params[1].getName());
		assertEquals("Incorrect type for y param", int.class.getName(), params[1].getType());
	}
