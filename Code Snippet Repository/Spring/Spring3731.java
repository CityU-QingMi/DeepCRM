	@Test
	public void testAttributeHasCorrespondingOperations() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();

		ModelMBeanOperationInfo get = info.getOperation("getName");
		assertNotNull("get operation should not be null", get);
		assertEquals("get operation should have visibility of four",
				get.getDescriptor().getFieldValue("visibility"),
				new Integer(4));
		assertEquals("get operation should have role \"getter\"", "getter", get.getDescriptor().getFieldValue("role"));

		ModelMBeanOperationInfo set = info.getOperation("setName");
		assertNotNull("set operation should not be null", set);
		assertEquals("set operation should have visibility of four",
				set.getDescriptor().getFieldValue("visibility"),
				new Integer(4));
		assertEquals("set operation should have role \"setter\"", "setter", set.getDescriptor().getFieldValue("role"));
	}
