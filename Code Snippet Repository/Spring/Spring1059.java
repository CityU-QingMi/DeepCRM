	@Test
	public void testComplexGenericIndexedMapEntryWithCollectionConversion() {
		Set<String> inputValue = new HashSet<>();
		inputValue.add("10");

		ComplexMapHolder holder = new ComplexMapHolder();
		BeanWrapper bw = new BeanWrapperImpl(holder);
		bw.setPropertyValue("genericIndexedMap[1]", inputValue);

		assertEquals(new Integer(1), holder.getGenericIndexedMap().keySet().iterator().next());
		assertEquals(new Long(10), holder.getGenericIndexedMap().values().iterator().next().get(0));
	}
