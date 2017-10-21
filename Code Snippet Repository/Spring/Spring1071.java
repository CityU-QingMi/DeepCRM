	@Test
	public void testGenericMapFromProperties() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		Properties input = new Properties();
		input.setProperty("4", "5");
		input.setProperty("6", "7");
		bw.setPropertyValue("shortMap", input);
		assertEquals(new Integer(5), gb.getShortMap().get(new Short("4")));
		assertEquals(new Integer(7), gb.getShortMap().get(new Short("6")));
	}
