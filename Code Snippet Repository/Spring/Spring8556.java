	@Test
	public void atWebAppConfigTestCaseWithoutExistingRequestAttributes() throws Exception {
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(AtWebAppConfigWebTestCase.class);

		RequestContextHolder.resetRequestAttributes();
		listener.beforeTestClass(testContext);
		assertRequestAttributesDoNotExist();

		assertWebAppConfigTestCase();
	}
