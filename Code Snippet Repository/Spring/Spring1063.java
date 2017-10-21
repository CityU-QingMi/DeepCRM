	@Test
	public void testGenericallyTypedIntegerBean() throws Exception {
		GenericIntegerBean gb = new GenericIntegerBean();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("genericProperty", "10");
		bw.setPropertyValue("genericListProperty", new String[] {"20", "30"});
		assertEquals(new Integer(10), gb.getGenericProperty());
		assertEquals(new Integer(20), gb.getGenericListProperty().get(0));
		assertEquals(new Integer(30), gb.getGenericListProperty().get(1));
	}
