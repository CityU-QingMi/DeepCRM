	@Test
	public void testSetCustomMessageCodesResolverAfterInitializeBindingResult() {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		binder.initBeanPropertyAccess();
		DefaultMessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		messageCodesResolver.setPrefix("errors.");
		binder.setMessageCodesResolver(messageCodesResolver);

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("age", "invalid");
		binder.bind(mpv);
		assertEquals("errors.typeMismatch", binder.getBindingResult().getFieldError("age").getCode());
	}
