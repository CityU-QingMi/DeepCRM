	@Test
	public void childWithDefaultLifecycleMethods() throws Exception {
		LifecycleAwareBean bean = (LifecycleAwareBean) this.beanFactory.getBean("childWithDefaultLifecycleMethods");
		assertTrue("Bean not initialized", bean.isInitCalled());
		assertFalse("Custom init method called incorrectly", bean.isCustomInitCalled());
		assertFalse("Bean destroyed too early", bean.isDestroyCalled());
		this.beanFactory.destroySingletons();
		assertTrue("Bean not destroyed", bean.isDestroyCalled());
		assertFalse("Custom destroy method called incorrectly", bean.isCustomDestroyCalled());
	}
