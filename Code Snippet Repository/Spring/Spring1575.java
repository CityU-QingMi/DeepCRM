	@Test
	public void mergeEmptyChild() {
		ManagedSet parent = new ManagedSet();
		parent.add("one");
		parent.add("two");
		ManagedSet child = new ManagedSet();
		child.setMergeEnabled(true);
		Set mergedSet = child.merge(parent);
		assertEquals("merge() obviously did not work.", 2, mergedSet.size());
	}
