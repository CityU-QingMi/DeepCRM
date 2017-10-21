	@Test
	public void convertListOfNonStringifiable() {
		List<Object> list = Arrays.asList(new TestEntity(1L), new TestEntity(2L));
		assertTrue(conversionService.canConvert(list.getClass(), String.class));
		try {
			conversionService.convert(list, String.class);
		}
		catch (ConversionFailedException ex) {
			assertTrue(ex.getMessage().contains(list.getClass().getName()));
			assertTrue(ex.getCause() instanceof ConverterNotFoundException);
			assertTrue(ex.getCause().getMessage().contains(TestEntity.class.getName()));
		}
	}
