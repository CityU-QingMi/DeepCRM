	@Test
	public void beforeAndAfterTestClassForDirtiesContextDeclaredLocallyOnClassAfterEachTestMethod() throws Exception {
		Class<?> clazz = DirtiesContextDeclaredLocallyAfterEachTestMethod.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		beforeListener.beforeTestClass(testContext);
		afterListener.beforeTestClass(testContext);
		afterListener.afterTestClass(testContext);
		beforeListener.afterTestClass(testContext);
		verify(testContext, times(0)).markApplicationContextDirty(any(HierarchyMode.class));
	}
