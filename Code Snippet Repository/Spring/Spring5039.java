	@Test
	public void parameterizedType() throws Exception {
		ParameterizedType type = (ParameterizedType) SerializableTypeWrapper.forField(Fields.class.getField("parameterizedType"));
		assertThat(type.toString(), equalTo("java.util.List<java.lang.String>"));
		assertSerializable(type);
		assertSerializable(type.getOwnerType());
		assertSerializable(type.getRawType());
		assertSerializable(type.getActualTypeArguments());
		assertSerializable(type.getActualTypeArguments()[0]);
	}
