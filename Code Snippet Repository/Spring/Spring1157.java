	@Test
	public void testExplicitScopeInheritanceForChildBeanDefinitions() throws Exception {
		String theChildScope = "bonanza!";

		RootBeanDefinition parent = new RootBeanDefinition();
		parent.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);

		AbstractBeanDefinition child = BeanDefinitionBuilder.childBeanDefinition("parent").getBeanDefinition();
		child.setBeanClass(TestBean.class);
		child.setScope(theChildScope);

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition("parent", parent);
		factory.registerBeanDefinition("child", child);

		AbstractBeanDefinition def = (AbstractBeanDefinition) factory.getBeanDefinition("child");
		assertEquals("Child 'scope' not overriding parent scope (it must).", theChildScope, def.getScope());
	}
