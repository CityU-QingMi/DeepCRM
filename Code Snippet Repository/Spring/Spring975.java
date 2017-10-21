	@Test
	public void setIndividualValidPropertyValues() {
		TestBean target = new TestBean();
		String newName = "tony";
		int newAge = 65;
		String newTouchy = "valid";
		try {
			AbstractPropertyAccessor accessor = createAccessor(target);
			accessor.setPropertyValue("age", new Integer(newAge));
			accessor.setPropertyValue(new PropertyValue("name", newName));
			accessor.setPropertyValue(new PropertyValue("touchy", newTouchy));
			assertTrue("Name property should have changed", target.getName().equals(newName));
			assertTrue("Touchy property should have changed", target.getTouchy().equals(newTouchy));
			assertTrue("Age property should have changed", target.getAge() == newAge);
		}
		catch (BeansException ex) {
			fail("Shouldn't throw exception when everything is valid");
		}
	}
