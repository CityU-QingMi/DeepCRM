	@Test
	public void testWithPrototype() throws Exception {
		PropertiesFactoryBean pfb = new PropertiesFactoryBean();
		pfb.setSingleton(false);
		pfb.setLocation(TEST_PROPS);
		Properties localProps = new Properties();
		localProps.setProperty("key2", "value2");
		pfb.setProperties(localProps);
		pfb.afterPropertiesSet();
		Properties props = pfb.getObject();
		assertEquals("99", props.getProperty("tb.array[0].age"));
		assertEquals("value2", props.getProperty("key2"));
		Properties newProps = pfb.getObject();
		assertTrue(props != newProps);
		assertEquals("99", newProps.getProperty("tb.array[0].age"));
		assertEquals("value2", newProps.getProperty("key2"));
	}
