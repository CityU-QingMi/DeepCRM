	@Test
	public void rawCollectionAsSource() throws Exception {
		conversionService.addConverter(new MyStringToRawCollectionConverter());

		assertEquals(Collections.singleton("testX"),
				conversionService.convert("test", TypeDescriptor.valueOf(String.class), new TypeDescriptor(getClass().getField("stringCollection"))));
		assertEquals(Collections.singleton("testX"),
				conversionService.convert("test", TypeDescriptor.valueOf(String.class), new TypeDescriptor(getClass().getField("genericCollection"))));
		assertEquals(Collections.singleton("testX"),
				conversionService.convert("test", TypeDescriptor.valueOf(String.class), new TypeDescriptor(getClass().getField("rawCollection"))));

		// The following is unpleasant but a consequence of the raw collection converter above...
		assertEquals(Collections.singleton("testX"),
				conversionService.convert("test", TypeDescriptor.valueOf(String.class), new TypeDescriptor(getClass().getField("integerCollection"))));
	}
