	private void testFunctionality(String name) {
		SideEffectBean pooled = (SideEffectBean) beanFactory.getBean(name);
		assertEquals(INITIAL_COUNT, pooled.getCount());
		pooled.doWork();
		assertEquals(INITIAL_COUNT + 1, pooled.getCount());

		pooled = (SideEffectBean) beanFactory.getBean(name);
		// Just check that it works--we can't make assumptions
		// about the count
		pooled.doWork();
		//assertEquals(INITIAL_COUNT + 1, apartment.getCount() );
	}
