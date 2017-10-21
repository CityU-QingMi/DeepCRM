	@Test
	public void synthesizeAlreadySynthesizedAnnotation() throws Exception {
		Method method = WebController.class.getMethod("handleMappedWithValueAttribute");
		WebMapping webMapping = method.getAnnotation(WebMapping.class);
		assertNotNull(webMapping);
		WebMapping synthesizedWebMapping = synthesizeAnnotation(webMapping);
		assertNotSame(webMapping, synthesizedWebMapping);
		WebMapping synthesizedAgainWebMapping = synthesizeAnnotation(synthesizedWebMapping);
		assertThat(synthesizedAgainWebMapping, instanceOf(SynthesizedAnnotation.class));
		assertSame(synthesizedWebMapping, synthesizedAgainWebMapping);

		assertEquals("name attribute: ", "foo", synthesizedAgainWebMapping.name());
		assertArrayEquals("aliased path attribute: ", asArray("/test"), synthesizedAgainWebMapping.path());
		assertArrayEquals("actual value attribute: ", asArray("/test"), synthesizedAgainWebMapping.value());
	}
