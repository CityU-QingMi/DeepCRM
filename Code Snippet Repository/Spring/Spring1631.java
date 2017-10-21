	@Test
	public void defaultsEventReceived() throws Exception {
		List defaultsList = this.eventListener.getDefaults();
		assertTrue(!defaultsList.isEmpty());
		assertTrue(defaultsList.get(0) instanceof DocumentDefaultsDefinition);
		DocumentDefaultsDefinition defaults = (DocumentDefaultsDefinition) defaultsList.get(0);
		assertEquals("true", defaults.getLazyInit());
		assertEquals("constructor", defaults.getAutowire());
		assertEquals("myInit", defaults.getInitMethod());
		assertEquals("myDestroy", defaults.getDestroyMethod());
		assertEquals("true", defaults.getMerge());
		assertTrue(defaults.getSource() instanceof Element);
	}
