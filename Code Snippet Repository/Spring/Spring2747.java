	@Test
	public void testAspectsAndAdvisorAreAppliedEvenIfComingFromParentFactory() {
		ClassPathXmlApplicationContext ac = newContext("aspectsPlusAdvisor.xml");
		GenericApplicationContext childAc = new GenericApplicationContext(ac);
		// Create a child factory with a bean that should be woven
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.getPropertyValues().addPropertyValue(new PropertyValue("name", "Adrian"))
				.addPropertyValue(new PropertyValue("age", new Integer(34)));
		childAc.registerBeanDefinition("adrian2", bd);
		// Register the advisor auto proxy creator with subclass
		childAc.registerBeanDefinition(AnnotationAwareAspectJAutoProxyCreator.class.getName(), new RootBeanDefinition(
				AnnotationAwareAspectJAutoProxyCreator.class));
		childAc.refresh();

		ITestBean beanFromChildContextThatShouldBeWeaved = (ITestBean) childAc.getBean("adrian2");
		//testAspectsAndAdvisorAreApplied(childAc, (ITestBean) ac.getBean("adrian"));
		doTestAspectsAndAdvisorAreApplied(childAc, beanFromChildContextThatShouldBeWeaved);
	}
