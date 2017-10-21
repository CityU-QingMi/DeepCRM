	@Test
	void serializesUnknownTypes() throws Exception {
		UniqueIdStringifier stringifier = new UniqueIdStringifier();

		String serialized = stringifier.apply(new MyCustomId(42));

		Object deserializedObject = deserialize(decodeBase64(serialized));
		assertThat(deserializedObject).isInstanceOf(MyCustomId.class);
		assertEquals(42, ((MyCustomId) deserializedObject).getValue());
	}
