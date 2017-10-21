	@SuppressWarnings("")
	@Test
	public void setCollectionPropertyWithIntArrayValue() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		Collection<Integer> coll = new HashSet<>();
		coll.add(0);
		accessor.setPropertyValue("collection", new int[] {0});
		List<Integer> set = new LinkedList<>();
		set.add(1);
		accessor.setPropertyValue("set", new int[] {1});
		List<Integer> sortedSet = new ArrayList<>();
		sortedSet.add(2);
		accessor.setPropertyValue("sortedSet", new int[] {2});
		Set<Integer> list = new HashSet<>();
		list.add(3);
		accessor.setPropertyValue("list", new int[] {3});
		assertEquals(1, target.getCollection().size());
		assertTrue(target.getCollection().containsAll(coll));
		assertEquals(1, target.getSet().size());
		assertTrue(target.getSet().containsAll(set));
		assertEquals(1, target.getSortedSet().size());
		assertTrue(target.getSortedSet().containsAll(sortedSet));
		assertEquals(1, target.getList().size());
		assertTrue(target.getList().containsAll(list));
	}
