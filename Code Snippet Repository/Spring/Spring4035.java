	@Test
	public void testBindingWithRequiredFields() throws Exception {
		TestBean tb = new TestBean();
		tb.setSpouse(new TestBean());

		DataBinder binder = new DataBinder(tb, "person");
		binder.setRequiredFields("touchy", "name", "age", "date", "spouse.name");

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("touchy", "");
		pvs.add("name", null);
		pvs.add("age", null);
		pvs.add("spouse.name", "     ");

		binder.bind(pvs);

		BindingResult br = binder.getBindingResult();
		assertEquals("Wrong number of errors", 5, br.getErrorCount());

		assertEquals("required", br.getFieldError("touchy").getCode());
		assertEquals("", br.getFieldValue("touchy"));
		assertEquals("required", br.getFieldError("name").getCode());
		assertEquals("", br.getFieldValue("name"));
		assertEquals("required", br.getFieldError("age").getCode());
		assertEquals("", br.getFieldValue("age"));
		assertEquals("required", br.getFieldError("date").getCode());
		assertEquals("", br.getFieldValue("date"));
		assertEquals("required", br.getFieldError("spouse.name").getCode());
		assertEquals("", br.getFieldValue("spouse.name"));
	}
