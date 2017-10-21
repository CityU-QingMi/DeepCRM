	@Test
	@SuppressWarnings("")
	public void convertFromListToStream() throws NoSuchFieldException {
		this.conversionService.addConverterFactory(new StringToNumberConverterFactory());
		List<String> stream = Arrays.asList("1", "2", "3");
		TypeDescriptor streamOfInteger = new TypeDescriptor(Types.class.getField("streamOfIntegers")); ;
		Object result = this.conversionService.convert(stream, streamOfInteger);

		assertNotNull("Converted object must not be null", result);
		assertTrue("Converted object must be a stream", result instanceof Stream);
		@SuppressWarnings("unchecked")
		Stream<Integer> content = (Stream<Integer>) result;
		assertEquals(6, content.mapToInt((x) -> x).sum());
	}
