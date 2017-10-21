	@Test
	@SuppressWarnings("")
	public void synthesizeAnnotationFromMapWithNestedMap() throws Exception {
		ComponentScanSingleFilter componentScan = ComponentScanSingleFilterClass.class.getAnnotation(ComponentScanSingleFilter.class);
		assertNotNull(componentScan);
		assertEquals("value from ComponentScan: ", "*Foo", componentScan.value().pattern());

		AnnotationAttributes attributes = getAnnotationAttributes(
				ComponentScanSingleFilterClass.class, componentScan, false, true);
		assertNotNull(attributes);
		assertEquals(ComponentScanSingleFilter.class, attributes.annotationType());

		Map<String, Object> filterMap = (Map<String, Object>) attributes.get("value");
		assertNotNull(filterMap);
		assertEquals("*Foo", filterMap.get("pattern"));

		// Modify nested map
		filterMap.put("pattern", "newFoo");
		filterMap.put("enigma", 42);

		ComponentScanSingleFilter synthesizedComponentScan = synthesizeAnnotation(
				attributes, ComponentScanSingleFilter.class, ComponentScanSingleFilterClass.class);
		assertNotNull(synthesizedComponentScan);

		assertNotSame(componentScan, synthesizedComponentScan);
		assertEquals("value from synthesized ComponentScan: ", "newFoo", synthesizedComponentScan.value().pattern());
	}
