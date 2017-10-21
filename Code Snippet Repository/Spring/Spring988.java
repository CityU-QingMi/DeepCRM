	@Test
	public void getAnotherNestedDeepProperty() {
		ITestBean target = new TestBean("rod", 31);
		ITestBean kerry = new TestBean("kerry", 35);
		target.setSpouse(kerry);
		kerry.setSpouse(target);
		AbstractPropertyAccessor accessor = createAccessor(target);
		Integer KA = (Integer) accessor.getPropertyValue("spouse.age");
		assertTrue("kerry is 35", KA == 35);
		Integer RA = (Integer) accessor.getPropertyValue("spouse.spouse.age");
		assertTrue("rod is 31, not" + RA, RA == 31);
		ITestBean spousesSpouse = (ITestBean) accessor.getPropertyValue("spouse.spouse");
		assertTrue("spousesSpouse = initial point", target == spousesSpouse);
	}
