	@Test
	public void singleElementToSingleElementArrayConversionSupport() throws Exception {
		Filter filter = FilteredClass.class.getAnnotation(Filter.class);

		AnnotationAttributes nestedAttributes = new AnnotationAttributes();
		nestedAttributes.put("name", "Dilbert");

		// Store single elements
		attributes.put("names", "Dogbert");
		attributes.put("classes", Number.class);
		attributes.put("nestedAttributes", nestedAttributes);
		attributes.put("filters", filter);

		// Get back arrays of single elements
		assertThat(attributes.getStringArray("names"), equalTo(new String[] {"Dogbert"}));
		assertThat(attributes.getClassArray("classes"), equalTo(new Class<?>[] {Number.class}));

		AnnotationAttributes[] array = attributes.getAnnotationArray("nestedAttributes");
		assertNotNull(array);
		assertThat(array.length, is(1));
		assertThat(array[0].getString("name"), equalTo("Dilbert"));

		Filter[] filters = attributes.getAnnotationArray("filters", Filter.class);
		assertNotNull(filters);
		assertThat(filters.length, is(1));
		assertThat(filters[0].pattern(), equalTo("foo"));
	}
