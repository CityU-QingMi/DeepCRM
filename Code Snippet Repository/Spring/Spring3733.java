	@Test
	public void testManagedResourceDescriptor() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();
		Descriptor desc = info.getMBeanDescriptor();

		assertEquals("Logging should be set to true", "true", desc.getFieldValue("log"));
		assertEquals("Log file should be jmx.log", "jmx.log", desc.getFieldValue("logFile"));
		assertEquals("Currency Time Limit should be 15", "15", desc.getFieldValue("currencyTimeLimit"));
		assertEquals("Persist Policy should be OnUpdate", "OnUpdate", desc.getFieldValue("persistPolicy"));
		assertEquals("Persist Period should be 200", "200", desc.getFieldValue("persistPeriod"));
		assertEquals("Persist Location should be foo", "./foo", desc.getFieldValue("persistLocation"));
		assertEquals("Persist Name should be bar", "bar.jmx", desc.getFieldValue("persistName"));
	}
