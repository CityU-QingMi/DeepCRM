	@Test
	public void testRegistrationOnInterface() throws Exception {
		Object bean = getContext().getBean("testInterfaceBean");
		ModelMBeanInfo inf = getAssembler().getMBeanInfo(bean, "bean:name=interfaceTestBean");
		assertNotNull(inf);
		assertEquals("My Managed Bean", inf.getDescription());

		ModelMBeanOperationInfo op = inf.getOperation("foo");
		assertNotNull("foo operation not exposed", op);
		assertEquals("invoke foo", op.getDescription());

		assertNull("doNotExpose operation should not be exposed", inf.getOperation("doNotExpose"));

		ModelMBeanAttributeInfo attr = inf.getAttribute("Bar");
		assertNotNull("bar attribute not exposed", attr);
		assertEquals("Bar description", attr.getDescription());

		ModelMBeanAttributeInfo attr2 = inf.getAttribute("CacheEntries");
		assertNotNull("cacheEntries attribute not exposed", attr2);
		assertEquals("Metric Type should be COUNTER", "COUNTER",
				attr2.getDescriptor().getFieldValue("metricType"));
	}
