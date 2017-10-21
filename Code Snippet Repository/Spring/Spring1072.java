	@Test
	public void setValidAndInvalidPropertyValuesShouldContainExceptionDetails() {
		TestBean target = new TestBean();
		String newName = "tony";
		String invalidTouchy = ".valid";
		try {
			BeanWrapper accessor = createAccessor(target);
			MutablePropertyValues pvs = new MutablePropertyValues();
			pvs.addPropertyValue(new PropertyValue("age", "foobar"));
			pvs.addPropertyValue(new PropertyValue("name", newName));
			pvs.addPropertyValue(new PropertyValue("touchy", invalidTouchy));
			accessor.setPropertyValues(pvs);
			fail("Should throw exception when everything is valid");
		}
		catch (PropertyBatchUpdateException ex) {
			assertTrue("Must contain 2 exceptions", ex.getExceptionCount() == 2);
			// Test validly set property matches
			assertTrue("Vaid set property must stick", target.getName().equals(newName));
			assertTrue("Invalid set property must retain old value", target.getAge() == 0);
			assertTrue("New value of dodgy setter must be available through exception",
					ex.getPropertyAccessException("touchy").getPropertyChangeEvent().getNewValue().equals(invalidTouchy));
		}
	}
