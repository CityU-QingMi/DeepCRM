	@Test
	public void getSuperType() throws Exception {
		ResolvableType type = ResolvableType.forClass(ExtendsList.class).getSuperType();
		assertThat(type.resolve(), equalTo((Class) ArrayList.class));
		type = type.getSuperType();
		assertThat(type.resolve(), equalTo((Class) AbstractList.class));
		type = type.getSuperType();
		assertThat(type.resolve(), equalTo((Class) AbstractCollection.class));
		type = type.getSuperType();
		assertThat(type.resolve(), equalTo((Class) Object.class));
	}
