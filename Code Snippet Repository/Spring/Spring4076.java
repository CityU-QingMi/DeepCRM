	@Test
	public void testFieldErrorAccessVariations() throws Exception {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		assertNull(binder.getBindingResult().getGlobalError());
		assertNull(binder.getBindingResult().getFieldError());
		assertNull(binder.getBindingResult().getFieldError(""));

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("age", "invalid");
		binder.bind(mpv);
		assertNull(binder.getBindingResult().getGlobalError());
		assertNull(binder.getBindingResult().getFieldError(""));
		assertNull(binder.getBindingResult().getFieldError("b*"));
		assertEquals("age", binder.getBindingResult().getFieldError().getField());
		assertEquals("age", binder.getBindingResult().getFieldError("*").getField());
		assertEquals("age", binder.getBindingResult().getFieldError("a*").getField());
		assertEquals("age", binder.getBindingResult().getFieldError("ag*").getField());
		assertEquals("age", binder.getBindingResult().getFieldError("age").getField());
	}
