	@Test
	public void bindingWithErrors() throws Exception {
		FieldAccessBean rod = new FieldAccessBean();
		DataBinder binder = new DataBinder(rod, "person");
		binder.initDirectFieldAccess();
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("name", "Rod"));
		pvs.addPropertyValue(new PropertyValue("age", "32x"));
		binder.bind(pvs);

		try {
			binder.close();
			fail("Should have thrown BindException");
		}
		catch (BindException ex) {
			assertTrue("changed name correctly", rod.getName().equals("Rod"));
			//assertTrue("changed age correctly", rod.getAge() == 32);

			Map<?, ?> map = binder.getBindingResult().getModel();
			//assertTrue("There are 3 element in map", m.size() == 1);
			FieldAccessBean tb = (FieldAccessBean) map.get("person");
			assertTrue("Same object", tb.equals(rod));

			BindingResult br = (BindingResult) map.get(BindingResult.MODEL_KEY_PREFIX + "person");
			assertTrue("Added itself to map", br == binder.getBindingResult());
			assertTrue(br.hasErrors());
			assertTrue("Correct number of errors", br.getErrorCount() == 1);

			assertTrue("Has age errors", br.hasFieldErrors("age"));
			assertTrue("Correct number of age errors", br.getFieldErrorCount("age") == 1);
			assertEquals("32x", binder.getBindingResult().getFieldValue("age"));
			assertEquals("32x", binder.getBindingResult().getFieldError("age").getRejectedValue());
			assertEquals(0, tb.getAge());
		}
	}
