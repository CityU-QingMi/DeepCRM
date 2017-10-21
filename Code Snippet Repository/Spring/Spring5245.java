	@Test
	@SuppressWarnings("")
	public void convertFromListToRawStream() throws NoSuchFieldException {
		List<String> stream = Arrays.asList("1", "2", "3");
		TypeDescriptor streamOfInteger = new TypeDescriptor(Types.class.getField("rawStream")); ;
		Object result = this.conversionService.convert(stream, streamOfInteger);

		assertNotNull("Converted object must not be null", result);
		assertTrue("Converted object must be a stream", result instanceof Stream);
		@SuppressWarnings("unchecked")
		Stream<Object> content = (Stream<Object>) result;
		StringBuilder sb = new StringBuilder();
		content.forEach(sb::append);
		assertEquals("123", sb.toString());
	}
