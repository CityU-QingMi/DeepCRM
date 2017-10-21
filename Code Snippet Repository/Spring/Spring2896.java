	@Test
	public void testCglibScopedProxy() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(LIST_CONTEXT);
		bf.setSerializationId("Y");
		SimpleMapScope scope = new SimpleMapScope();
		bf.registerScope("request", scope);

		TestBean tb = (TestBean) bf.getBean("testBean");
		assertTrue(AopUtils.isCglibProxy(tb.getFriends()));
		assertTrue(tb.getFriends() instanceof ScopedObject);
		ScopedObject scoped = (ScopedObject) tb.getFriends();
		assertEquals(ArrayList.class, scoped.getTargetObject().getClass());
		tb.getFriends().add("myFriend");

		assertTrue(scope.getMap().containsKey("scopedTarget.scopedList"));
		assertEquals(ArrayList.class, scope.getMap().get("scopedTarget.scopedList").getClass());

		ArrayList<?> deserialized = (ArrayList<?>) SerializationTestUtils.serializeAndDeserialize(tb.getFriends());
		assertNotNull(deserialized);
		assertTrue(AopUtils.isCglibProxy(deserialized));
		assertTrue(deserialized.contains("myFriend"));
		assertTrue(deserialized instanceof ScopedObject);
		ScopedObject scopedDeserialized = (ScopedObject) deserialized;
		assertEquals(ArrayList.class, scopedDeserialized.getTargetObject().getClass());

		bf.setSerializationId(null);
	}
