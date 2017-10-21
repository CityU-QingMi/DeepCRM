	@Test
	public void javaDocSample() throws Exception {
		ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("myMap"));
		assertThat(t.getSuperType().toString(), equalTo("java.util.AbstractMap<java.lang.Integer, java.util.List<java.lang.String>>"));
		assertThat(t.asMap().toString(), equalTo("java.util.Map<java.lang.Integer, java.util.List<java.lang.String>>"));
		assertThat(t.getGeneric(0).resolve(), equalTo((Class)Integer.class));
		assertThat(t.getGeneric(1).resolve(), equalTo((Class)List.class));
		assertThat(t.getGeneric(1).toString(), equalTo("java.util.List<java.lang.String>"));
		assertThat(t.resolveGeneric(1, 0), equalTo((Class) String.class));
	}
