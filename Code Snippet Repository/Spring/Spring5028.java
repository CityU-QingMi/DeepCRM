	@Test
	public void serialize() throws Exception {
		testSerialization(ResolvableType.forClass(List.class));
		testSerialization(ResolvableType.forField(Fields.class.getField("charSequenceList")));
		testSerialization(ResolvableType.forMethodParameter(Methods.class.getMethod("charSequenceParameter", List.class), 0));
		testSerialization(ResolvableType.forMethodReturnType(Methods.class.getMethod("charSequenceReturn")));
		testSerialization(ResolvableType.forConstructorParameter(Constructors.class.getConstructor(List.class), 0));
		testSerialization(ResolvableType.forField(Fields.class.getField("charSequenceList")).getGeneric());
		testSerialization(ResolvableType.forField(Fields.class.getField("charSequenceList")).asCollection());
		testSerialization(ResolvableType.forClass(ExtendsMap.class).getSuperType());
		ResolvableType deserializedNone = testSerialization(ResolvableType.NONE);
		assertThat(deserializedNone, sameInstance(ResolvableType.NONE));
	}
