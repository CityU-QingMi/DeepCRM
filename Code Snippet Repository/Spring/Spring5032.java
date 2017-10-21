	@Test
	public void forMethodParameterWithNesting() throws Exception {
		Method method = Methods.class.getMethod("nested", Map.class);
		MethodParameter methodParameter = MethodParameter.forExecutable(method, 0);
		methodParameter.increaseNestingLevel();
		ResolvableType type = ResolvableType.forMethodParameter(methodParameter);
		assertThat(type.resolve(), equalTo((Class) Map.class));
		assertThat(type.getGeneric(0).resolve(), equalTo((Class) Byte.class));
		assertThat(type.getGeneric(1).resolve(), equalTo((Class) Long.class));
	}
