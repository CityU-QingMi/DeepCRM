	@Test
	public void testComplexGenericMapWithCollectionConversion() {
		Map<Set<String>, Set<String>> inputMap = new HashMap<>();
		Set<String> inputKey = new HashSet<>();
		inputKey.add("1");
		Set<String> inputValue = new HashSet<>();
		inputValue.add("10");
			inputMap.put(inputKey, inputValue);

		ComplexMapHolder holder = new ComplexMapHolder();
		BeanWrapper bw = new BeanWrapperImpl(holder);
		bw.setPropertyValue("genericMap", inputMap);

		assertEquals(new Integer(1), holder.getGenericMap().keySet().iterator().next().get(0));
		assertEquals(new Long(10), holder.getGenericMap().values().iterator().next().get(0));
	}
