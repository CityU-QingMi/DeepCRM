	@Test
	public void testGenericallyTypedSetOfIntegerBean() throws Exception {
		GenericSetOfIntegerBean gb = new GenericSetOfIntegerBean();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("genericProperty", "10");
		bw.setPropertyValue("genericListProperty", new String[] {"20", "30"});
		assertEquals(new Integer(10), gb.getGenericProperty().iterator().next());
		assertEquals(new Integer(20), gb.getGenericListProperty().get(0).iterator().next());
		assertEquals(new Integer(30), gb.getGenericListProperty().get(1).iterator().next());
	}
