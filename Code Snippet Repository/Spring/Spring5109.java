	@Test
	public void synthesizeAnnotationFromMapWithAttributeOfIncorrectType() throws Exception {
		Map<String, Object> map = Collections.singletonMap(VALUE, 42L);

		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(startsWith("Attributes map"));
		exception.expectMessage(containsString("returned a value of type [java.lang.Long]"));
		exception.expectMessage(containsString("for attribute 'value'"));
		exception.expectMessage(containsString("but a value of type [java.lang.String] is required"));
		exception.expectMessage(containsString("as defined by annotation type [" + Component.class.getName() + "]"));

		synthesizeAnnotation(map, Component.class, null);
	}
