	@Test
	public void testSerializableSingletonProxy() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(SERIALIZATION_CONTEXT, CLASS));
		Person p = (Person) bf.getBean("serializableSingleton");
		assertSame("Should be a Singleton", p, bf.getBean("serializableSingleton"));
		Person p2 = (Person) SerializationTestUtils.serializeAndDeserialize(p);
		assertEquals(p, p2);
		assertNotSame(p, p2);
		assertEquals("serializableSingleton", p2.getName());

		// Add unserializable advice
		Advice nop = new NopInterceptor();
		((Advised) p).addAdvice(nop);
		// Check it still works
		assertEquals(p2.getName(), p2.getName());
		assertFalse("Not serializable because an interceptor isn't serializable", SerializationTestUtils.isSerializable(p));

		// Remove offending interceptor...
		assertTrue(((Advised) p).removeAdvice(nop));
		assertTrue("Serializable again because offending interceptor was removed", SerializationTestUtils.isSerializable(p));
	}
