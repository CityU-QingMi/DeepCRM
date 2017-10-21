	@SuppressWarnings("")
	@Test
	public void setCollectionPropertyNonMatchingType() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		Collection<String> coll = new ArrayList<>();
		coll.add("coll1");
		accessor.setPropertyValue("collection", coll);
		List<String> set = new LinkedList<>();
		set.add("set1");
		accessor.setPropertyValue("set", set);
		List<String> sortedSet = new ArrayList<>();
		sortedSet.add("sortedSet1");
		accessor.setPropertyValue("sortedSet", sortedSet);
		Set<String> list = new HashSet<>();
		list.add("list1");
		accessor.setPropertyValue("list", list);
		assertEquals(1, target.getCollection().size());
		assertTrue(target.getCollection().containsAll(coll));
		assertEquals(1, target.getSet().size());
		assertTrue(target.getSet().containsAll(set));
		assertEquals(1, target.getSortedSet().size());
		assertTrue(target.getSortedSet().containsAll(sortedSet));
		assertEquals(1, target.getList().size());
		assertTrue(target.getList().containsAll(list));
	}
