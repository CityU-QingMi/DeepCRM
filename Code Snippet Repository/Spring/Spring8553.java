	@Test
	public void standardApplicationContext() throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(getClass());
		given(testContext.getApplicationContext()).willReturn(mock(ApplicationContext.class));

		listener.beforeTestClass(testContext);
		assertSetUpOutsideOfStelAttributeExists();

		listener.prepareTestInstance(testContext);
		assertSetUpOutsideOfStelAttributeExists();

		listener.beforeTestMethod(testContext);
		assertSetUpOutsideOfStelAttributeExists();

		listener.afterTestMethod(testContext);
		assertSetUpOutsideOfStelAttributeExists();
	}
