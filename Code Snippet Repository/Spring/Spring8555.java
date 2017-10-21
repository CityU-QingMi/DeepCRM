	@Test
	public void legacyWebTestCaseWithPresetRequestAttributes() throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(LegacyWebTestCase.class);

		listener.beforeTestClass(testContext);
		assertSetUpOutsideOfStelAttributeExists();

		listener.prepareTestInstance(testContext);
		assertSetUpOutsideOfStelAttributeExists();
		verify(testContext, times(0)).setAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE, Boolean.TRUE);
		given(testContext.getAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE)).willReturn(null);

		listener.beforeTestMethod(testContext);
		assertSetUpOutsideOfStelAttributeExists();
		verify(testContext, times(0)).setAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE, Boolean.TRUE);
		given(testContext.getAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE)).willReturn(null);

		listener.afterTestMethod(testContext);
		verify(testContext, times(1)).removeAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE);
		assertSetUpOutsideOfStelAttributeExists();
	}
