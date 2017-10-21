	@Test
	public void convertCollectionToCollection() throws Exception {
		Set<String> foo = new LinkedHashSet<>();
		foo.add("1");
		foo.add("2");
		foo.add("3");
		@SuppressWarnings("unchecked")
		List<Integer> bar = (List<Integer>) conversionService.convert(foo, TypeDescriptor.forObject(foo),
				new TypeDescriptor(getClass().getField("genericList")));
		assertEquals(new Integer(1), bar.get(0));
		assertEquals(new Integer(2), bar.get(1));
		assertEquals(new Integer(3), bar.get(2));
	}
