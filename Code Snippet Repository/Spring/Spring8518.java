	@Test
	public void beforeAndAfterTestMethodForDirtiesContextDeclaredOnMethodViaMetaAnnotationWithAfterMethodMode()
			throws Exception {
		Class<?> clazz = getClass();
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		given(testContext.getTestMethod()).willReturn(
			clazz.getDeclaredMethod("dirtiesContextDeclaredViaMetaAnnotationWithAfterMethodMode"));
		beforeListener.beforeTestMethod(testContext);
		afterListener.beforeTestMethod(testContext);
		verify(testContext, times(0)).markApplicationContextDirty(any(HierarchyMode.class));
		afterListener.afterTestMethod(testContext);
		beforeListener.afterTestMethod(testContext);
		verify(testContext, times(1)).markApplicationContextDirty(EXHAUSTIVE);
	}
