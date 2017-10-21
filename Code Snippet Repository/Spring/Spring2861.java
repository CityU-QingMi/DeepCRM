	@Test
	public void testSerializableSingletonProxyFactoryBean() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(SERIALIZATION_CONTEXT, CLASS));
		Person p = (Person) bf.getBean("serializableSingleton");
		ProxyFactoryBean pfb = (ProxyFactoryBean) bf.getBean("&serializableSingleton");
		ProxyFactoryBean pfb2 = (ProxyFactoryBean) SerializationTestUtils.serializeAndDeserialize(pfb);
		Person p2 = (Person) pfb2.getObject();
		assertEquals(p, p2);
		assertNotSame(p, p2);
		assertEquals("serializableSingleton", p2.getName());
	}
