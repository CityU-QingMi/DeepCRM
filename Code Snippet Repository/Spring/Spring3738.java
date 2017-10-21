	@Test
	public void testMetricDescriptor() throws Exception {
		ModelMBeanInfo info = getMBeanInfoFromAssembler();
		Descriptor desc = info.getAttribute(QUEUE_SIZE_METRIC).getDescriptor();
		assertEquals("Currency Time Limit should be 20", "20", desc.getFieldValue("currencyTimeLimit"));
		assertEquals("Persist Policy should be OnUpdate", "OnUpdate", desc.getFieldValue("persistPolicy"));
		assertEquals("Persist Period should be 300", "300", desc.getFieldValue("persistPeriod"));
		assertEquals("Unit should be messages", "messages",desc.getFieldValue("units"));
		assertEquals("Display Name should be Queue Size", "Queue Size",desc.getFieldValue("displayName"));
		assertEquals("Metric Type should be COUNTER", "COUNTER",desc.getFieldValue("metricType"));
		assertEquals("Metric Category should be utilization", "utilization",desc.getFieldValue("metricCategory"));
	}
