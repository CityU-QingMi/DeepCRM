	@Test
	public void mergeSunnyDay() {
		ManagedMap parent = new ManagedMap();
		parent.put("one", "one");
		parent.put("two", "two");
		ManagedMap child = new ManagedMap();
		child.put("three", "three");
		child.setMergeEnabled(true);
		Map mergedMap = (Map) child.merge(parent);
		assertEquals("merge() obviously did not work.", 3, mergedMap.size());
	}
