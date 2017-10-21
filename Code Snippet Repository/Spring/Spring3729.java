	@Test
	public void testGetMBeanOperationInfo() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();
		MBeanOperationInfo[] inf = info.getOperations();
		assertEquals("Invalid number of Operations returned",
				getExpectedOperationCount(), inf.length);

		for (int x = 0; x < inf.length; x++) {
			assertNotNull("MBeanOperationInfo should not be null", inf[x]);
			assertNotNull(
					"Description for MBeanOperationInfo should not be null",
					inf[x].getDescription());
		}
	}
