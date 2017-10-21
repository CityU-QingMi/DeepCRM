	@Test
	public void testConfigMixin() {
		SideEffectBean pooled = (SideEffectBean) beanFactory.getBean("pooledWithMixin");
		assertEquals(INITIAL_COUNT, pooled.getCount());
		PoolingConfig conf = (PoolingConfig) beanFactory.getBean("pooledWithMixin");
		// TODO one invocation from setup
		//assertEquals(1, conf.getInvocations());
		pooled.doWork();
		//	assertEquals("No objects active", 0, conf.getActive());
		assertEquals("Correct target source", 25, conf.getMaxSize());
		//	assertTrue("Some free", conf.getFree() > 0);
		//assertEquals(2, conf.getInvocations());
		assertEquals(25, conf.getMaxSize());
	}
