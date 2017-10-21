	@Test
	public void testConversionsAvailable() throws Exception {
		TypeConvertorUsingConversionService tcs = new TypeConvertorUsingConversionService();

		// ArrayList containing List<Integer> to List<String>
		Class<?> clazz = typeDescriptorForListOfString.getElementTypeDescriptor().getType();
		assertEquals(String.class,clazz);
		List<?> l = (List<?>) tcs.convertValue(listOfInteger, TypeDescriptor.forObject(listOfInteger), typeDescriptorForListOfString);
		assertNotNull(l);

		// ArrayList containing List<String> to List<Integer>
		clazz = typeDescriptorForListOfInteger.getElementTypeDescriptor().getType();
		assertEquals(Integer.class,clazz);

		l = (List<?>) tcs.convertValue(listOfString, TypeDescriptor.forObject(listOfString), typeDescriptorForListOfString);
		assertNotNull(l);
	}
