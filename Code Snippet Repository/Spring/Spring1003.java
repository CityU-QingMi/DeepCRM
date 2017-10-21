	@SuppressWarnings("")
	@Test
	public void setCollectionPropertyWithStringValue() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		List<String> set = new LinkedList<>();
		set.add("set1");
		accessor.setPropertyValue("set", "set1");
		List<String> sortedSet = new ArrayList<>();
		sortedSet.add("sortedSet1");
		accessor.setPropertyValue("sortedSet", "sortedSet1");
		Set<String> list = new HashSet<>();
		list.add("list1");
		accessor.setPropertyValue("list", "list1");
		assertEquals(1, target.getSet().size());
		assertTrue(target.getSet().containsAll(set));
		assertEquals(1, target.getSortedSet().size());
		assertTrue(target.getSortedSet().containsAll(sortedSet));
		assertEquals(1, target.getList().size());
		assertTrue(target.getList().containsAll(list));
	}
