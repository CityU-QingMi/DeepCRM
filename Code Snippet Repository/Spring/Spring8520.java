	@Test
	public void beforeAndAfterTestMethodForDirtiesContextDeclaredLocallyOnClassAfterEachTestMethod() throws Exception {
		Class<?> clazz = DirtiesContextDeclaredLocallyAfterEachTestMethod.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("clean"));
		beforeListener.beforeTestMethod(testContext);
		afterListener.beforeTestMethod(testContext);
		verify(testContext, times(0)).markApplicationContextDirty(any(HierarchyMode.class));
		afterListener.afterTestMethod(testContext);
		beforeListener.afterTestMethod(testContext);
		verify(testContext, times(1)).markApplicationContextDirty(EXHAUSTIVE);
	}
