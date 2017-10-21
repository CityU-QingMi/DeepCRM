	@Test
	public void testScopedProperties() {
		Properties props = (Properties) this.beanFactory.getBean("myScopedProperties");
		assertEquals("Incorrect property value", "bar", props.get("foo"));
		assertEquals("Incorrect property value", null, props.get("foo2"));
		Properties props2 = (Properties) this.beanFactory.getBean("myScopedProperties");
		assertEquals("Incorrect property value", "bar", props.get("foo"));
		assertEquals("Incorrect property value", null, props.get("foo2"));
		assertTrue(props != props2);
	}
