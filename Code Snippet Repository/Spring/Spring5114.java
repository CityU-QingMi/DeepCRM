	@Test
	public void equalsForSynthesizedAnnotations() throws Exception {
		Method methodWithPath = WebController.class.getMethod("handleMappedWithPathAttribute");
		WebMapping webMappingWithAliases = methodWithPath.getAnnotation(WebMapping.class);
		assertNotNull(webMappingWithAliases);

		Method methodWithPathAndValue = WebController.class.getMethod("handleMappedWithSamePathAndValueAttributes");
		WebMapping webMappingWithPathAndValue = methodWithPathAndValue.getAnnotation(WebMapping.class);
		assertNotNull(webMappingWithPathAndValue);

		WebMapping synthesizedWebMapping1 = synthesizeAnnotation(webMappingWithAliases);
		assertNotNull(synthesizedWebMapping1);
		WebMapping synthesizedWebMapping2 = synthesizeAnnotation(webMappingWithAliases);
		assertNotNull(synthesizedWebMapping2);

		// Equality amongst standard annotations
		assertThat(webMappingWithAliases, is(webMappingWithAliases));
		assertThat(webMappingWithPathAndValue, is(webMappingWithPathAndValue));

		// Inequality amongst standard annotations
		assertThat(webMappingWithAliases, is(not(webMappingWithPathAndValue)));
		assertThat(webMappingWithPathAndValue, is(not(webMappingWithAliases)));

		// Equality amongst synthesized annotations
		assertThat(synthesizedWebMapping1, is(synthesizedWebMapping1));
		assertThat(synthesizedWebMapping2, is(synthesizedWebMapping2));
		assertThat(synthesizedWebMapping1, is(synthesizedWebMapping2));
		assertThat(synthesizedWebMapping2, is(synthesizedWebMapping1));

		// Equality between standard and synthesized annotations
		assertThat(synthesizedWebMapping1, is(webMappingWithPathAndValue));
		assertThat(webMappingWithPathAndValue, is(synthesizedWebMapping1));

		// Inequality between standard and synthesized annotations
		assertThat(synthesizedWebMapping1, is(not(webMappingWithAliases)));
		assertThat(webMappingWithAliases, is(not(synthesizedWebMapping1)));
	}
