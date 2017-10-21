	@Test
	public void testAttributeInfoHasDescriptors() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();

		ModelMBeanAttributeInfo attr = info.getAttribute(NAME_ATTRIBUTE);
		Descriptor desc = attr.getDescriptor();
		assertNotNull("getMethod field should not be null",
				desc.getFieldValue("getMethod"));
		assertNotNull("setMethod field should not be null",
				desc.getFieldValue("setMethod"));
		assertEquals("getMethod field has incorrect value", "getName",
				desc.getFieldValue("getMethod"));
		assertEquals("setMethod field has incorrect value", "setName",
				desc.getFieldValue("setMethod"));
	}
