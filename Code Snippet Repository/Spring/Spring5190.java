	@Test
	@SuppressWarnings("")
	public void convertCollectionToCollectionNotGeneric() throws Exception {
		Set<String> foo = new LinkedHashSet<>();
		foo.add("1");
		foo.add("2");
		foo.add("3");
		List bar = (List) conversionService.convert(foo, TypeDescriptor.valueOf(LinkedHashSet.class), TypeDescriptor
				.valueOf(List.class));
		assertEquals("1", bar.get(0));
		assertEquals("2", bar.get(1));
		assertEquals("3", bar.get(2));
	}
