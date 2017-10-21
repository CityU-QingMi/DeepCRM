	@Test
	public void toStrings() throws Exception {
		assertThat(ResolvableType.NONE.toString(), equalTo("?"));

		assertFieldToStringValue("classType", "java.util.List<?>");
		assertFieldToStringValue("typeVariableType", "?");
		assertFieldToStringValue("parameterizedType", "java.util.List<?>");
		assertFieldToStringValue("arrayClassType", "java.util.List<?>[]");
		assertFieldToStringValue("genericArrayType", "java.util.List<java.lang.String>[]");
		assertFieldToStringValue("genericMultiArrayType", "java.util.List<java.lang.String>[][][]");
		assertFieldToStringValue("wildcardType", "java.util.List<java.lang.Number>");
		assertFieldToStringValue("wildcardSuperType", "java.util.List<java.lang.Number>");
		assertFieldToStringValue("charSequenceList", "java.util.List<java.lang.CharSequence>");
		assertFieldToStringValue("stringList", "java.util.List<java.lang.String>");
		assertFieldToStringValue("stringListList", "java.util.List<java.util.List<java.lang.String>>");
		assertFieldToStringValue("stringArrayList", "java.util.List<java.lang.String[]>");
		assertFieldToStringValue("stringIntegerMultiValueMap", "org.springframework.util.MultiValueMap<java.lang.String, java.lang.Integer>");
		assertFieldToStringValue("stringIntegerMultiValueMapSwitched", VariableNameSwitch.class.getName() + "<java.lang.Integer, java.lang.String>");
		assertFieldToStringValue("listOfListOfUnknown", "java.util.List<java.util.List<?>>");

		assertTypedFieldToStringValue("typeVariableType", "java.lang.String");
		assertTypedFieldToStringValue("parameterizedType", "java.util.List<java.lang.String>");

		assertThat(ResolvableType.forClass(ListOfGenericArray.class).toString(), equalTo(ListOfGenericArray.class.getName()));
		assertThat(ResolvableType.forClass(List.class, ListOfGenericArray.class).toString(), equalTo("java.util.List<java.util.List<java.lang.String>[]>"));
	}
