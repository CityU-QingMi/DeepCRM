	@Test
	public void convertCannotOptimizeArray() {
		conversionService.addConverter(new Converter<Byte, Byte>() {
			@Override
			public Byte convert(Byte source) {
				return (byte) (source + 1);
			}
		});
		byte[] byteArray = new byte[] {1, 2, 3};
		byte[] converted = conversionService.convert(byteArray, byte[].class);
		assertNotSame(byteArray, converted);
		assertTrue(Arrays.equals(new byte[] {2, 3, 4}, converted));
	}
