	@Test
	public void testReuseInSameThread() {
		SideEffectBean apartment = (SideEffectBean) beanFactory.getBean("apartment");
		assertEquals(INITIAL_COUNT, apartment.getCount() );
		apartment.doWork();
		assertEquals(INITIAL_COUNT + 1, apartment.getCount() );

		apartment = (SideEffectBean) beanFactory.getBean("apartment");
		assertEquals(INITIAL_COUNT + 1, apartment.getCount() );
	}
