	@Test
	public void testGenericListOfArrays() throws MalformedURLException {
		GenericBean<String> gb = new GenericBean<>();
		ArrayList<String[]> list = new ArrayList<>();
		list.add(new String[] {"str1", "str2"});
		gb.setListOfArrays(list);
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("listOfArrays[0][1]", "str3 ");
		assertEquals("str3 ", bw.getPropertyValue("listOfArrays[0][1]"));
		assertEquals("str3 ", gb.getListOfArrays().get(0)[1]);
	}
