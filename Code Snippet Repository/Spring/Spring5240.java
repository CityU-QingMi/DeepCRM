	@Test
	public void convertFromStreamToList() throws NoSuchFieldException {
		this.conversionService.addConverter(Number.class, String.class, new ObjectToStringConverter());
		Stream<Integer> stream = Arrays.asList(1, 2, 3).stream();
		TypeDescriptor listOfStrings = new TypeDescriptor(Types.class.getField("listOfStrings")); ;
		Object result = this.conversionService.convert(stream, listOfStrings);

		assertNotNull("Converted object must not be null", result);
		assertTrue("Converted object must be a list", result instanceof List);
		@SuppressWarnings("unchecked")
		List<String> content = (List<String>) result;
		assertEquals("1", content.get(0));
		assertEquals("2", content.get(1));
		assertEquals("3", content.get(2));
		assertEquals("Wrong number of elements", 3, content.size());
	}
