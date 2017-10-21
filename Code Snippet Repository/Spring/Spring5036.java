	@Test
	public void resolveTypeWithCustomVariableResolver() throws Exception {
		VariableResolver variableResolver = mock(VariableResolver.class);
		given(variableResolver.getSource()).willReturn(this);
		ResolvableType longType = ResolvableType.forClass(Long.class);
		given(variableResolver.resolveVariable(any())).willReturn(longType);

		ResolvableType variable = ResolvableType.forType(
				Fields.class.getField("typeVariableType").getGenericType(), variableResolver);
		ResolvableType parameterized = ResolvableType.forType(
				Fields.class.getField("parameterizedType").getGenericType(), variableResolver);

		assertThat(variable.resolve(), equalTo((Class) Long.class));
		assertThat(parameterized.resolve(), equalTo((Class) List.class));
		assertThat(parameterized.resolveGeneric(), equalTo((Class) Long.class));
		verify(variableResolver, atLeastOnce()).resolveVariable(this.typeVariableCaptor.capture());
		assertThat(this.typeVariableCaptor.getValue().getName(), equalTo("T"));
	}
