	@Test
	public void testMetricDescription() throws Exception {
		ModelMBeanInfo inf = getMBeanInfoFromAssembler();
		ModelMBeanAttributeInfo metric = inf.getAttribute(QUEUE_SIZE_METRIC);
		ModelMBeanOperationInfo operation = inf.getOperation("getQueueSize");
		assertEquals("The description for the queue size metric is incorrect",
				"The QueueSize metric", metric.getDescription());
		assertEquals("The description for the getter operation of the queue size metric is incorrect",
				"The QueueSize metric", operation.getDescription());
	}
