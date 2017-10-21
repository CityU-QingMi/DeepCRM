	@Test
	public void synthesizeAnnotationWithAttributeAliases() throws Exception {
		Method method = WebController.class.getMethod("handleMappedWithValueAttribute");
		WebMapping webMapping = method.getAnnotation(WebMapping.class);
		assertNotNull(webMapping);

		WebMapping synthesizedWebMapping1 = synthesizeAnnotation(webMapping);
		assertThat(synthesizedWebMapping1, instanceOf(SynthesizedAnnotation.class));
		assertNotSame(webMapping, synthesizedWebMapping1);

		assertEquals("name attribute: ", "foo", synthesizedWebMapping1.name());
		assertArrayEquals("aliased path attribute: ", asArray("/test"), synthesizedWebMapping1.path());
		assertArrayEquals("actual value attribute: ", asArray("/test"), synthesizedWebMapping1.value());

		WebMapping synthesizedWebMapping2 = synthesizeAnnotation(webMapping);
		assertThat(synthesizedWebMapping2, instanceOf(SynthesizedAnnotation.class));
		assertNotSame(webMapping, synthesizedWebMapping2);

		assertEquals("name attribute: ", "foo", synthesizedWebMapping2.name());
		assertArrayEquals("aliased path attribute: ", asArray("/test"), synthesizedWebMapping2.path());
		assertArrayEquals("actual value attribute: ", asArray("/test"), synthesizedWebMapping2.value());
	}
