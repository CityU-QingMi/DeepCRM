	@Test
	public void getSource() throws Exception {
		Class<?> classType = MySimpleInterfaceType.class;
		Field basicField = Fields.class.getField("classType");
		Field field = Fields.class.getField("charSequenceList");
		Method method = Methods.class.getMethod("charSequenceParameter", List.class);
		MethodParameter methodParameter = MethodParameter.forExecutable(method, 0);
		assertThat(ResolvableType.forField(basicField).getSource(), equalTo((Object) basicField));
		assertThat(ResolvableType.forField(field).getSource(), equalTo((Object) field));
		assertThat(ResolvableType.forMethodParameter(methodParameter).getSource(), equalTo((Object) methodParameter));
		assertThat(ResolvableType.forMethodParameter(method, 0).getSource(), equalTo((Object) methodParameter));
		assertThat(ResolvableType.forClass(classType).getSource(), equalTo((Object) classType));
		assertThat(ResolvableType.forClass(classType).getSuperType().getSource(), equalTo((Object) classType.getGenericSuperclass()));
	}
