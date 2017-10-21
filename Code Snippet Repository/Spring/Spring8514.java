	@Test
	public void beforeAndAfterTestClassForDirtiesContextDeclaredViaMetaAnnotationOnClassAfterClass() throws Exception {
		Class<?> clazz = DirtiesContextDeclaredViaMetaAnnotationAfterClass.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		beforeListener.beforeTestClass(testContext);
		afterListener.beforeTestClass(testContext);
		verify(testContext, times(0)).markApplicationContextDirty(any(HierarchyMode.class));
		afterListener.afterTestClass(testContext);
		beforeListener.afterTestClass(testContext);
		verify(testContext, times(1)).markApplicationContextDirty(EXHAUSTIVE);
	}
