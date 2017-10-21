	@Test
	public void beforeAndAfterTestMethodForDirtiesContextDeclaredViaMetaAnnotationOnClassAfterClass() throws Exception {
		Class<?> clazz = DirtiesContextDeclaredViaMetaAnnotationAfterClass.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("clean"));
		beforeListener.beforeTestMethod(testContext);
		afterListener.beforeTestMethod(testContext);
		afterListener.afterTestMethod(testContext);
		beforeListener.afterTestMethod(testContext);
		verify(testContext, times(0)).markApplicationContextDirty(any(HierarchyMode.class));
	}
