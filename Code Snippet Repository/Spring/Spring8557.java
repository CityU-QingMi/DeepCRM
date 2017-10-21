	@Test
	public void activateListenerWithoutExistingRequestAttributes() throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(NoAtWebAppConfigWebTestCase.class);
		given(testContext.getAttribute(ServletTestExecutionListener.ACTIVATE_LISTENER)).willReturn(true);

		RequestContextHolder.resetRequestAttributes();
		listener.beforeTestClass(testContext);
		assertRequestAttributesDoNotExist();

		assertWebAppConfigTestCase();
	}
