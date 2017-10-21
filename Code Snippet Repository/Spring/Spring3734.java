	@Test
	public void testAttributeDescriptor() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();
		Descriptor desc = info.getAttribute(NAME_ATTRIBUTE).getDescriptor();

		assertEquals("Default value should be foo", "foo", desc.getFieldValue("default"));
		assertEquals("Currency Time Limit should be 20", "20", desc.getFieldValue("currencyTimeLimit"));
		assertEquals("Persist Policy should be OnUpdate", "OnUpdate", desc.getFieldValue("persistPolicy"));
		assertEquals("Persist Period should be 300", "300", desc.getFieldValue("persistPeriod"));
	}
