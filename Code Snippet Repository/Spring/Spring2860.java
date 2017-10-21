	@Test
	public void testSerializablePrototypeProxy() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(SERIALIZATION_CONTEXT, CLASS));
		Person p = (Person) bf.getBean("serializablePrototype");
		assertNotSame("Should not be a Singleton", p, bf.getBean("serializablePrototype"));
		Person p2 = (Person) SerializationTestUtils.serializeAndDeserialize(p);
		assertEquals(p, p2);
		assertNotSame(p, p2);
		assertEquals("serializablePrototype", p2.getName());
	}
