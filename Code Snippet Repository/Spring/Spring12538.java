	@Test
	public void initializingBeanAndInitMethod() throws Exception {
		assertFalse(InitAndIB.constructed);
		InitAndIB iib = (InitAndIB) this.applicationContext.getBean("init-and-ib");
		assertTrue(InitAndIB.constructed);
		assertTrue(iib.afterPropertiesSetInvoked && iib.initMethodInvoked);
		assertTrue(!iib.destroyed && !iib.customDestroyed);
		this.applicationContext.close();
		assertTrue(!iib.destroyed && !iib.customDestroyed);
		ConfigurableApplicationContext parent = (ConfigurableApplicationContext) this.applicationContext.getParent();
		parent.close();
		assertTrue(iib.destroyed && iib.customDestroyed);
		parent.close();
		assertTrue(iib.destroyed && iib.customDestroyed);
	}
