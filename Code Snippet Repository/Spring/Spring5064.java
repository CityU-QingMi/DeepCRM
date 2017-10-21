	@Test
	public void getAliasedStringArrayWithImplicitAliases() {
		String[] value = new String[] {"test.xml"};
		List<String> aliases = Arrays.asList("value", "location1", "location2", "location3", "xmlFile", "groovyScript");

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("location1", value);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertArrayEquals(value, attributes.getStringArray(alias)));

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("value", value);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertArrayEquals(value, attributes.getStringArray(alias)));

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("location1", value);
		attributes.put("value", value);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertArrayEquals(value, attributes.getStringArray(alias)));

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("location1", value);
		AnnotationUtils.registerDefaultValues(attributes);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertArrayEquals(value, attributes.getStringArray(alias)));

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		attributes.put("value", value);
		AnnotationUtils.registerDefaultValues(attributes);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertArrayEquals(value, attributes.getStringArray(alias)));

		attributes = new AnnotationAttributes(ImplicitAliasesContextConfig.class);
		AnnotationUtils.registerDefaultValues(attributes);
		AnnotationUtils.postProcessAnnotationAttributes(null, attributes, false);
		aliases.stream().forEach(alias -> assertArrayEquals(new String[] {""}, attributes.getStringArray(alias)));
	}
