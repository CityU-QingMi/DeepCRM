	@Test
	public void testSetCustomMessageCodesResolverBeforeInitializeBindingResultForDirectFieldAccess() {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		DefaultMessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		messageCodesResolver.setPrefix("errors.");
		binder.setMessageCodesResolver(messageCodesResolver);
		binder.initDirectFieldAccess();

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("age", "invalid");
		binder.bind(mpv);
		assertEquals("errors.typeMismatch", binder.getBindingResult().getFieldError("age").getCode());
	}
