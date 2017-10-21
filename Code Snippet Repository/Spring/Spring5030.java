	@Test
	public void forPrivateField() throws Exception {
		Field field = Fields.class.getDeclaredField("privateField");
		ResolvableType type = ResolvableType.forField(field);
		assertThat(type.getType(), equalTo(field.getGenericType()));
		assertThat(type.resolve(), equalTo((Class) List.class));

		Field field2 = Fields.class.getDeclaredField("otherPrivateField");
		ResolvableType type2 = ResolvableType.forField(field2);
		assertThat(type2.getType(), equalTo(field2.getGenericType()));
		assertThat(type2.resolve(), equalTo((Class) List.class));

		assertEquals(type, type2);
		assertEquals(type.hashCode(), type2.hashCode());
	}
