	@Test
	public void setPropertyIntermediatePropertyIsNullWithAutoGrow() {
		Person target = createPerson("John", "Paris", "FR");
		target.address.country = null;
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setAutoGrowNestedPaths(true);

		accessor.setPropertyValue("address.country.name", "UK");
		assertThat(target.address.country.name, is("UK"));
	}
