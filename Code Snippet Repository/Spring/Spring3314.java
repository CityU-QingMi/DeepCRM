	@Test
	public void testLazyOptionalResourceInjectionWithNonExistingTarget() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(OptionalFieldResourceInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);

		OptionalFieldResourceInjectionBean bean = (OptionalFieldResourceInjectionBean) bf.getBean("annotatedBean");
		assertNotNull(bean.getTestBean());
		assertNotNull(bean.getTestBeans());
		assertTrue(bean.getTestBeans().isEmpty());
		try {
			bean.getTestBean().getName();
			fail("Should have thrown NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException ex) {
			// expected
		}
	}
