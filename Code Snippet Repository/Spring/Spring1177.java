	@Test
	public void testGetTypeWorksAfterParentChildMerging() {
		RootBeanDefinition parentDefinition = new RootBeanDefinition(TestBean.class);
		ChildBeanDefinition childDefinition = new ChildBeanDefinition("parent", DerivedTestBean.class, null, null);

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition("parent", parentDefinition);
		factory.registerBeanDefinition("child", childDefinition);
		factory.freezeConfiguration();

		assertEquals(TestBean.class, factory.getType("parent"));
		assertEquals(DerivedTestBean.class, factory.getType("child"));
	}
