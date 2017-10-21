	@Test
	public void testRejectWithoutDefaultMessage() throws Exception {
		TestBean tb = new TestBean();
		tb.setName("myName");
		tb.setAge(99);

		BeanPropertyBindingResult ex = new BeanPropertyBindingResult(tb, "tb");
		ex.reject("invalid");
		ex.rejectValue("age", "invalidField");

		StaticMessageSource ms = new StaticMessageSource();
		ms.addMessage("invalid", Locale.US, "general error");
		ms.addMessage("invalidField", Locale.US, "invalid field");

		assertEquals("general error", ms.getMessage(ex.getGlobalError(), Locale.US));
		assertEquals("invalid field", ms.getMessage(ex.getFieldError("age"), Locale.US));
	}
