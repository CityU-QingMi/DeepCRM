	@Test
	public void testContains() {
		assertFalse(CollectionUtils.contains((Iterator<String>) null, "myElement"));
		assertFalse(CollectionUtils.contains((Enumeration<String>) null, "myElement"));
		assertFalse(CollectionUtils.contains(new LinkedList<String>().iterator(), "myElement"));
		assertFalse(CollectionUtils.contains(new Hashtable<String, Object>().keys(), "myElement"));

		List<String> list = new LinkedList<>();
		list.add("myElement");
		assertTrue(CollectionUtils.contains(list.iterator(), "myElement"));

		Hashtable<String, String> ht = new Hashtable<>();
		ht.put("myElement", "myValue");
		assertTrue(CollectionUtils.contains(ht.keys(), "myElement"));
	}
