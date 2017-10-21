	@Test
	public void setAnotherNestedProperty() throws Exception {
		ITestBean target = new TestBean("rod", 31);
		ITestBean kerry = new TestBean("kerry", 0);

		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setPropertyValue("spouse", kerry);

		assertTrue("nested set worked", target.getSpouse() == kerry);
		assertTrue("no back relation", kerry.getSpouse() == null);
		accessor.setPropertyValue(new PropertyValue("spouse.spouse", target));
		assertTrue("nested set worked", kerry.getSpouse() == target);
		assertTrue("kerry age not set", kerry.getAge() == 0);
		accessor.setPropertyValue(new PropertyValue("spouse.age", 35));
		assertTrue("Set primitive on spouse", kerry.getAge() == 35);

		assertEquals(kerry, accessor.getPropertyValue("spouse"));
		assertEquals(target, accessor.getPropertyValue("spouse.spouse"));
	}
