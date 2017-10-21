	@Test
	public void testMetricDescriptorDefaults() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();
		Descriptor desc = info.getAttribute(CACHE_ENTRIES_METRIC).getDescriptor();
		assertNull("Currency Time Limit should not be populated", desc.getFieldValue("currencyTimeLimit"));
		assertNull("Persist Policy should not be populated", desc.getFieldValue("persistPolicy"));
		assertNull("Persist Period should not be populated", desc.getFieldValue("persistPeriod"));
		assertNull("Unit should not be populated", desc.getFieldValue("units"));
		assertEquals("Display Name should be populated by default via JMX", CACHE_ENTRIES_METRIC,desc.getFieldValue("displayName"));
		assertEquals("Metric Type should be GAUGE", "GAUGE",desc.getFieldValue("metricType"));
		assertNull("Metric Category should not be populated", desc.getFieldValue("metricCategory"));
	}
