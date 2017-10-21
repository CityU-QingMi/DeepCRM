	@Test
	public void setNestedPropertyPolymorphic() throws Exception {
		ITestBean target = new TestBean("rod", 31);
		ITestBean kerry = new Employee();

		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setPropertyValue("spouse", kerry);
		accessor.setPropertyValue("spouse.age", new Integer(35));
		accessor.setPropertyValue("spouse.name", "Kerry");
		accessor.setPropertyValue("spouse.company", "Lewisham");
		assertTrue("kerry name is Kerry", kerry.getName().equals("Kerry"));

		assertTrue("nested set worked", target.getSpouse() == kerry);
		assertTrue("no back relation", kerry.getSpouse() == null);
		accessor.setPropertyValue(new PropertyValue("spouse.spouse", target));
		assertTrue("nested set worked", kerry.getSpouse() == target);

		AbstractPropertyAccessor kerryAccessor = createAccessor(kerry);
		assertTrue("spouse.spouse.spouse.spouse.company=Lewisham",
				"Lewisham".equals(kerryAccessor.getPropertyValue("spouse.spouse.spouse.spouse.company")));
	}
