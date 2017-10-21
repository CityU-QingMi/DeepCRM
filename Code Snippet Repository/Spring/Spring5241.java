	@Test
	public void convertFromStreamToArray() throws NoSuchFieldException {
		this.conversionService.addConverterFactory(new NumberToNumberConverterFactory());
		Stream<Integer> stream = Arrays.asList(1, 2, 3).stream();
		TypeDescriptor arrayOfLongs = new TypeDescriptor(Types.class.getField("arrayOfLongs")); ;
		Object result = this.conversionService.convert(stream, arrayOfLongs);

		assertNotNull("Converted object must not be null", result);
		assertTrue("Converted object must be an array", result.getClass().isArray());
		Long[] content = (Long[]) result;
		assertEquals(Long.valueOf(1L), content[0]);
		assertEquals(Long.valueOf(2L), content[1]);
		assertEquals(Long.valueOf(3L), content[2]);
		assertEquals("Wrong number of elements", 3, content.length);
	}
