	@Test
	public void testBindingWithRequiredMapFields() throws Exception {
		TestBean tb = new TestBean();
		tb.setSpouse(new TestBean());

		DataBinder binder = new DataBinder(tb, "person");
		binder.setRequiredFields("someMap[key1]", "someMap[key2]", "someMap['key3']", "someMap[key4]");

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("someMap[key1]", "value1");
		pvs.add("someMap['key2']", "value2");
		pvs.add("someMap[key3]", "value3");

		binder.bind(pvs);

		BindingResult br = binder.getBindingResult();
		assertEquals("Wrong number of errors", 1, br.getErrorCount());
		assertEquals("required", br.getFieldError("someMap[key4]").getCode());
	}
