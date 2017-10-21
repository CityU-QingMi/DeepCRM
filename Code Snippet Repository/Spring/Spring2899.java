	@Test
	public void testProxySerializableWithoutConfigMixin() throws Exception {
		Person pooled = (Person) beanFactory.getBean("pooledPerson");

		//System.out.println(((Advised) pooled).toProxyConfigString());
		assertTrue(((Advised) pooled).getTargetSource() instanceof CommonsPool2TargetSource);

		//((Advised) pooled).setTargetSource(new SingletonTargetSource(new SerializablePerson()));
		Person serialized = (Person) SerializationTestUtils.serializeAndDeserialize(pooled);
		assertTrue(((Advised) serialized).getTargetSource() instanceof SingletonTargetSource);
		serialized.setAge(25);
		assertEquals(25, serialized.getAge());
	}
