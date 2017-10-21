	@Test
	public void testSetCustomMessageCodesResolverBeforeInitializeBindingResultForBeanPropertyAccess() {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		DefaultMessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		messageCodesResolver.setPrefix("errors.");
		binder.setMessageCodesResolver(messageCodesResolver);
		binder.setAutoGrowCollectionLimit(512); // allow configuration after set a MessageCodesResolver
		binder.initBeanPropertyAccess();

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("age", "invalid");
		binder.bind(mpv);
		assertEquals("errors.typeMismatch", binder.getBindingResult().getFieldError("age").getCode());
		assertEquals(512, BeanWrapper.class.cast(binder.getInternalBindingResult().getPropertyAccessor()).getAutoGrowCollectionLimit());
	}
