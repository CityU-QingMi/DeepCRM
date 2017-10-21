	@Test
	public void closeTriggersDestroy() {
		LifecycleBean lb = (LifecycleBean) applicationContext.getBean("lifecycle");
		assertTrue("Not destroyed", !lb.isDestroyed());
		applicationContext.close();
		if (applicationContext.getParent() != null) {
			((ConfigurableApplicationContext) applicationContext.getParent()).close();
		}
		assertTrue("Destroyed", lb.isDestroyed());
		applicationContext.close();
		if (applicationContext.getParent() != null) {
			((ConfigurableApplicationContext) applicationContext.getParent()).close();
		}
		assertTrue("Destroyed", lb.isDestroyed());
	}
