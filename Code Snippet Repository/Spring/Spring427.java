	@Test
	public void testUseDifferentManagedInstancesInSameThread() {
		SideEffectBean apartment = (SideEffectBean) beanFactory.getBean("apartment");
		assertEquals(INITIAL_COUNT, apartment.getCount() );
		apartment.doWork();
		assertEquals(INITIAL_COUNT + 1, apartment.getCount() );

		ITestBean test = (ITestBean) beanFactory.getBean("threadLocal2");
		assertEquals("Rod", test.getName());
		assertEquals("Kerry", test.getSpouse().getName());
	}
