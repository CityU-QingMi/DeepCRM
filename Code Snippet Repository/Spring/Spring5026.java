	@Test
	public void hashCodeAndEquals() throws Exception {
		ResolvableType forClass = ResolvableType.forClass(List.class);
		ResolvableType forFieldDirect = ResolvableType.forField(Fields.class.getDeclaredField("stringList"));
		ResolvableType forFieldViaType = ResolvableType.forType(Fields.class.getDeclaredField("stringList").getGenericType(), (VariableResolver) null);
		ResolvableType forFieldWithImplementation = ResolvableType.forField(Fields.class.getDeclaredField("stringList"), TypedFields.class);

		assertThat(forClass, equalTo(forClass));
		assertThat(forClass.hashCode(), equalTo(forClass.hashCode()));
		assertThat(forClass, not(equalTo(forFieldDirect)));
		assertThat(forClass, not(equalTo(forFieldWithImplementation)));

		assertThat(forFieldDirect, equalTo(forFieldDirect));
		assertThat(forFieldDirect, not(equalTo(forFieldViaType)));
		assertThat(forFieldDirect, not(equalTo(forFieldWithImplementation)));
	}
