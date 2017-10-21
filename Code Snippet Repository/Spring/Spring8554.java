	@Test
	public void legacyWebTestCaseWithoutExistingRequestAttributes() throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(LegacyWebTestCase.class);

		RequestContextHolder.resetRequestAttributes();
		assertRequestAttributesDoNotExist();

		listener.beforeTestClass(testContext);

		listener.prepareTestInstance(testContext);
		assertRequestAttributesDoNotExist();
		verify(testContext, times(0)).setAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE, Boolean.TRUE);
		given(testContext.getAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE)).willReturn(null);

		listener.beforeTestMethod(testContext);
		assertRequestAttributesDoNotExist();
		verify(testContext, times(0)).setAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE, Boolean.TRUE);

		listener.afterTestMethod(testContext);
		verify(testContext, times(1)).removeAttribute(RESET_REQUEST_CONTEXT_HOLDER_ATTRIBUTE);
		assertRequestAttributesDoNotExist();
	}
