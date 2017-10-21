	@Test
	public void noneReturnValues() throws Exception {
		ResolvableType none = ResolvableType.NONE;
		assertThat(none.as(Object.class), equalTo(ResolvableType.NONE));
		assertThat(none.asCollection(), equalTo(ResolvableType.NONE));
		assertThat(none.asMap(), equalTo(ResolvableType.NONE));
		assertThat(none.getComponentType(), equalTo(ResolvableType.NONE));
		assertThat(none.getGeneric(0), equalTo(ResolvableType.NONE));
		assertThat(none.getGenerics().length, equalTo(0));
		assertThat(none.getInterfaces().length, equalTo(0));
		assertThat(none.getSuperType(), equalTo(ResolvableType.NONE));
		assertThat(none.getType(), equalTo(ResolvableType.EmptyType.INSTANCE));
		assertThat(none.hasGenerics(), equalTo(false));
		assertThat(none.isArray(), equalTo(false));
		assertThat(none.resolve(), nullValue());
		assertThat(none.resolve(String.class), equalTo((Class) String.class));
		assertThat(none.resolveGeneric(0), nullValue());
		assertThat(none.resolveGenerics().length, equalTo(0));
		assertThat(none.toString(), equalTo("?"));
		assertThat(none.hasUnresolvableGenerics(), equalTo(false));
		assertThat(none.isAssignableFrom(ResolvableType.forClass(Object.class)), equalTo(false));
	}
