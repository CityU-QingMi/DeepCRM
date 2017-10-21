	@Test
	public void setValidPropertyValues() {
		TestBean target = new TestBean();
		String newName = "tony";
		int newAge = 65;
		String newTouchy = "valid";
		try {
			AbstractPropertyAccessor accessor = createAccessor(target);
			MutablePropertyValues pvs = new MutablePropertyValues();
			pvs.addPropertyValue(new PropertyValue("age", newAge));
			pvs.addPropertyValue(new PropertyValue("name", newName));
			pvs.addPropertyValue(new PropertyValue("touchy", newTouchy));
			accessor.setPropertyValues(pvs);
			assertTrue("Name property should have changed", target.getName().equals(newName));
			assertTrue("Touchy property should have changed", target.getTouchy().equals(newTouchy));
			assertTrue("Age property should have changed", target.getAge() == newAge);
		}
		catch (BeansException ex) {
			fail("Shouldn't throw exception when everything is valid");
		}
	}
