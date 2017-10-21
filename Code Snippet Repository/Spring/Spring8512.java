	@Test
	public void beforeAndAfterTestClassForDirtiesContextDeclaredLocallyOnClassBeforeClass() throws Exception {
		Class<?> clazz = DirtiesContextDeclaredLocallyBeforeClass.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		beforeListener.beforeTestClass(testContext);
		afterListener.beforeTestClass(testContext);
		verify(testContext, times(1)).markApplicationContextDirty(EXHAUSTIVE);
		afterListener.afterTestClass(testContext);
		beforeListener.afterTestClass(testContext);
		verify(testContext, times(1)).markApplicationContextDirty(EXHAUSTIVE);
	}
