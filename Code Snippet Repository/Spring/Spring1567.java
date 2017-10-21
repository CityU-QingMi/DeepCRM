	@Test
	public void mergeChildValuesOverrideTheParents() {
		// doesn't make much sense in the context of a list...
		ManagedList parent = new ManagedList();
		parent.add("one");
		parent.add("two");
		ManagedList child = new ManagedList();
		child.add("one");
		child.setMergeEnabled(true);
		List mergedList = child.merge(parent);
		assertEquals("merge() obviously did not work.", 3, mergedList.size());
	}
