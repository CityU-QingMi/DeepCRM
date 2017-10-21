	@Test
	public void testComplexGenericMap() {
		Map<List<String>, List<String>> inputMap = new HashMap<>();
		List<String> inputKey = new LinkedList<>();
		inputKey.add("1");
		List<String> inputValue = new LinkedList<>();
		inputValue.add("10");
		inputMap.put(inputKey, inputValue);

		ComplexMapHolder holder = new ComplexMapHolder();
		BeanWrapper bw = new BeanWrapperImpl(holder);
		bw.setPropertyValue("genericMap", inputMap);

		assertEquals(new Integer(1), holder.getGenericMap().keySet().iterator().next().get(0));
		assertEquals(new Long(10), holder.getGenericMap().values().iterator().next().get(0));
	}
