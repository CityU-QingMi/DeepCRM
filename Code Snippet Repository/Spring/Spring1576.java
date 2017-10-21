	@Test
	public void mergeChildValuesOverrideTheParents() {
		// asserts that the set contract is not violated during a merge() operation...
		ManagedSet parent = new ManagedSet();
		parent.add("one");
		parent.add("two");
		ManagedSet child = new ManagedSet();
		child.add("one");
		child.setMergeEnabled(true);
		Set mergedSet = child.merge(parent);
		assertEquals("merge() obviously did not work.", 2, mergedSet.size());
	}
