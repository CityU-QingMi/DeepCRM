	@Test
	public void testConversionToOldCollections() throws PropertyVetoException {
		OldCollectionsBean tb = new OldCollectionsBean();
		BeanWrapper bw = new BeanWrapperImpl(tb);
		bw.registerCustomEditor(Vector.class, new CustomCollectionEditor(Vector.class));
		bw.registerCustomEditor(Hashtable.class, new CustomMapEditor(Hashtable.class));

		bw.setPropertyValue("vector", new String[] {"a", "b"});
		assertEquals(2, tb.getVector().size());
		assertEquals("a", tb.getVector().get(0));
		assertEquals("b", tb.getVector().get(1));

		bw.setPropertyValue("hashtable", Collections.singletonMap("foo", "bar"));
		assertEquals(1, tb.getHashtable().size());
		assertEquals("bar", tb.getHashtable().get("foo"));
	}
