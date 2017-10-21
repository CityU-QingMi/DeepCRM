	@Test
	public void testComplexDerivedIndexedMapEntryWithCollectionConversion() {
		Set<String> inputValue = new HashSet<>();
		inputValue.add("10");

		ComplexMapHolder holder = new ComplexMapHolder();
		BeanWrapper bw = new BeanWrapperImpl(holder);
		bw.setPropertyValue("derivedIndexedMap[1]", inputValue);

		assertEquals(new Integer(1), holder.getDerivedIndexedMap().keySet().iterator().next());
		assertEquals(new Long(10), holder.getDerivedIndexedMap().values().iterator().next().get(0));
	}
