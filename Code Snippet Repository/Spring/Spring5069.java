	@Test
	public void getAnnotationAttributesWithAttributeAliasesWithDifferentValues() throws Exception {
		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(containsString("attribute 'value' and its alias 'path'"));
		exception.expectMessage(containsString("values of [{/enigma}] and [{/test}]"));

		Method method = WebController.class.getMethod("handleMappedWithDifferentPathAndValueAttributes");
		WebMapping webMapping = method.getAnnotation(WebMapping.class);
		getAnnotationAttributes(webMapping);
	}
