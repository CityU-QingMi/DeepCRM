	@Test
	public void testObjectFactoryWithTypedMapMethod() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ObjectFactoryMapMethodInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));
		bf.setSerializationId("test");

		ObjectFactoryMapMethodInjectionBean bean = (ObjectFactoryMapMethodInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("testBean"), bean.getTestBean());
		bean = (ObjectFactoryMapMethodInjectionBean) SerializationTestUtils.serializeAndDeserialize(bean);
		assertSame(bf.getBean("testBean"), bean.getTestBean());
		bf.destroySingletons();
	}
