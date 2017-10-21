	@Test
	@SuppressWarnings("")
	public void convertFromArrayToStream() throws NoSuchFieldException {
		Integer[] stream = new Integer[] {1, 0, 1};
		this.conversionService.addConverter(new Converter<Integer, Boolean>() {
			@Override
			public Boolean convert(Integer source) {
				return source == 1;
			}
		});
		TypeDescriptor streamOfBoolean = new TypeDescriptor(Types.class.getField("streamOfBooleans")); ;
		Object result = this.conversionService.convert(stream, streamOfBoolean);

		assertNotNull("Converted object must not be null", result);
		assertTrue("Converted object must be a stream", result instanceof Stream);
		@SuppressWarnings("unchecked")
		Stream<Boolean> content = (Stream<Boolean>) result;
		assertEquals(2, content.filter(x -> x).count());
	}
