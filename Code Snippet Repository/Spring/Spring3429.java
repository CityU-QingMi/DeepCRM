	@Test
	public void innerBeanAsListener() {
		StaticApplicationContext context = new StaticApplicationContext();
		RootBeanDefinition listenerDef = new RootBeanDefinition(TestBean.class);
		listenerDef.getPropertyValues().add("friends", new RootBeanDefinition(BeanThatListens.class));
		context.registerBeanDefinition("listener", listenerDef);
		context.refresh();

		context.publishEvent(new MyEvent(this));
		context.publishEvent(new MyEvent(this));
		TestBean listener = context.getBean(TestBean.class);
		assertEquals(3, ((BeanThatListens) listener.getFriends().iterator().next()).getEventCount());

		context.close();
	}
