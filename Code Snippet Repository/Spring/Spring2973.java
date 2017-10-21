	@Test
	public void testSingletonFromParent() {
		DefaultListableBeanFactory parent = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(parent).loadBeanDefinitions(PARENT_CONTEXT);
		TestBean beanFromParent = (TestBean) parent.getBean("inheritedTestBeanSingleton");
		DefaultListableBeanFactory child = new DefaultListableBeanFactory(parent);
		new XmlBeanDefinitionReader(child).loadBeanDefinitions(CHILD_CONTEXT);
		TestBean beanFromChild = (TestBean) child.getBean("inheritedTestBeanSingleton");
		assertTrue("singleton from parent and child is the same", beanFromParent == beanFromChild);
	}
