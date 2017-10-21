	@Test
	public void getAliasedStringWithImplicitAliases() {
		String value = "metaverse";
		List<String> aliases = Arrays.asList("value", "location1", "location2", "location3", "xmlFile", "groovyScript");

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("value", value);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertEquals(value, attributes.getString(alias)));

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("location1", value);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertEquals(value, attributes.getString(alias)));

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("value", value);
		attributes.put("location1", value);
		attributes.put("xmlFile", value);
		attributes.put("groovyScript", value);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertEquals(value, attributes.getString(alias)));
	}
