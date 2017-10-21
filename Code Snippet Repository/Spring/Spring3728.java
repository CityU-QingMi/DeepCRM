	@Test
	public void testGetMBeanAttributeInfo() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();
		MBeanAttributeInfo[] inf = info.getAttributes();
		assertEquals("Invalid number of Attributes returned",
				getExpectedAttributeCount(), inf.length);

		for (int x = 0; x < inf.length; x++) {
			assertNotNull("MBeanAttributeInfo should not be null", inf[x]);
			assertNotNull(
					"Description for MBeanAttributeInfo should not be null",
					inf[x].getDescription());
		}
	}
