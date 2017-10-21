	@Test
	public void testDestroyMethodOnInnerBeanAsPrototype() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition innerBd = new RootBeanDefinition(BeanWithDestroyMethod.class);
		innerBd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		innerBd.setDestroyMethodName("close");
		RootBeanDefinition bd = new RootBeanDefinition(BeanWithDestroyMethod.class);
		bd.setDestroyMethodName("close");
		bd.getPropertyValues().add("inner", innerBd);
		lbf.registerBeanDefinition("test", bd);
		BeanWithDestroyMethod.closeCount = 0;
		lbf.preInstantiateSingletons();
		lbf.destroySingletons();
		assertEquals("Destroy methods invoked", 1, BeanWithDestroyMethod.closeCount);
	}
