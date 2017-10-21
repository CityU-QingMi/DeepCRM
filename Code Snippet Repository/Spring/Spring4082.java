	@Test
	public void testSetMessageCodesResolverIsNullAfterInitializeBindingResult() {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		binder.initBeanPropertyAccess();
		binder.setMessageCodesResolver(null);

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("age", "invalid");
		binder.bind(mpv);
		assertEquals("typeMismatch", binder.getBindingResult().getFieldError("age").getCode()); // Keep a default MessageCodesResolver
	}
