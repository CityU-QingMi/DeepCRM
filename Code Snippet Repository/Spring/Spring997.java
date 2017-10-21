	@Test
	public void setCollectionProperty() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		Collection<String> coll = new HashSet<>();
		coll.add("coll1");
		accessor.setPropertyValue("collection", coll);
		Set<String> set = new HashSet<>();
		set.add("set1");
		accessor.setPropertyValue("set", set);
		SortedSet<String> sortedSet = new TreeSet<>();
		sortedSet.add("sortedSet1");
		accessor.setPropertyValue("sortedSet", sortedSet);
		List<String> list = new LinkedList<>();
		list.add("list1");
		accessor.setPropertyValue("list", list);
		assertSame(coll, target.getCollection());
		assertSame(set, target.getSet());
		assertSame(sortedSet, target.getSortedSet());
		assertSame(list, target.getList());
	}
