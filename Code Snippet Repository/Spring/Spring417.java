	@Test
	public void testBasicFunctionality() {
		SideEffectBean proxied = (SideEffectBean) beanFactory.getBean("swappable");
		assertEquals(INITIAL_COUNT, proxied.getCount() );
		proxied.doWork();
		assertEquals(INITIAL_COUNT + 1, proxied.getCount() );

		proxied = (SideEffectBean) beanFactory.getBean("swappable");
		proxied.doWork();
		assertEquals(INITIAL_COUNT + 2, proxied.getCount() );
	}
