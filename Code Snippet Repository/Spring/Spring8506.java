	@Test
	public void beforeAndAfterTestMethodForDirtiesContextDeclaredLocallyOnMethodWithBeforeMethodMode() throws Exception {
		Class<?> clazz = getClass();
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		given(testContext.getTestMethod()).willReturn(
			clazz.getDeclaredMethod("dirtiesContextDeclaredLocallyWithBeforeMethodMode"));
		beforeListener.beforeTestMethod(testContext);
		afterListener.beforeTestMethod(testContext);
		verify(testContext, times(1)).markApplicationContextDirty(EXHAUSTIVE);
		afterListener.afterTestMethod(testContext);
		beforeListener.afterTestMethod(testContext);
		verify(testContext, times(1)).markApplicationContextDirty(EXHAUSTIVE);
	}
