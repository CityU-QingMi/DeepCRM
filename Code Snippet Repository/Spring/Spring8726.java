	@After
	public void verifyCustomRequestAttributesAreRestored() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		assertThat(requestAttributes, instanceOf(ServletRequestAttributes.class));
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

		assertThat(request.getAttribute(FROM_CUSTOM_MOCK), is(FROM_CUSTOM_MOCK));
		assertThat(request.getAttribute(FROM_MVC_TEST_DEFAULT), is(nullValue()));
		assertThat(request.getAttribute(FROM_MVC_TEST_MOCK), is(nullValue()));

		RequestContextHolder.resetRequestAttributes();
		this.wac.close();
	}
