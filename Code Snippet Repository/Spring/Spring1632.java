	@Test
	public void beanEventReceived() throws Exception {
		ComponentDefinition componentDefinition1 = this.eventListener.getComponentDefinition("testBean");
		assertTrue(componentDefinition1 instanceof BeanComponentDefinition);
		assertEquals(1, componentDefinition1.getBeanDefinitions().length);
		BeanDefinition beanDefinition1 = componentDefinition1.getBeanDefinitions()[0];
		assertEquals(new TypedStringValue("Rob Harrop"),
				beanDefinition1.getConstructorArgumentValues().getGenericArgumentValue(String.class).getValue());
		assertEquals(1, componentDefinition1.getBeanReferences().length);
		assertEquals("testBean2", componentDefinition1.getBeanReferences()[0].getBeanName());
		assertEquals(1, componentDefinition1.getInnerBeanDefinitions().length);
		BeanDefinition innerBd1 = componentDefinition1.getInnerBeanDefinitions()[0];
		assertEquals(new TypedStringValue("ACME"),
				innerBd1.getConstructorArgumentValues().getGenericArgumentValue(String.class).getValue());
		assertTrue(componentDefinition1.getSource() instanceof Element);

		ComponentDefinition componentDefinition2 = this.eventListener.getComponentDefinition("testBean2");
		assertTrue(componentDefinition2 instanceof BeanComponentDefinition);
		assertEquals(1, componentDefinition1.getBeanDefinitions().length);
		BeanDefinition beanDefinition2 = componentDefinition2.getBeanDefinitions()[0];
		assertEquals(new TypedStringValue("Juergen Hoeller"),
				beanDefinition2.getPropertyValues().getPropertyValue("name").getValue());
		assertEquals(0, componentDefinition2.getBeanReferences().length);
		assertEquals(1, componentDefinition2.getInnerBeanDefinitions().length);
		BeanDefinition innerBd2 = componentDefinition2.getInnerBeanDefinitions()[0];
		assertEquals(new TypedStringValue("Eva Schallmeiner"),
				innerBd2.getPropertyValues().getPropertyValue("name").getValue());
		assertTrue(componentDefinition2.getSource() instanceof Element);
	}
