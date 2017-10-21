	@Test
	public void setSimpleProperty() {
		Simple target = new Simple("John", 2);
		AbstractPropertyAccessor accessor = createAccessor(target);

		accessor.setPropertyValue("name", "SomeValue");

		assertThat(target.name, is("SomeValue"));
		assertThat(target.getName(), is("SomeValue"));
	}
