	@Test
	public void mergeSunnyDay() {
		ManagedSet parent = new ManagedSet();
		parent.add("one");
		parent.add("two");
		ManagedSet child = new ManagedSet();
		child.add("three");
		child.setMergeEnabled(true);
		Set mergedSet = child.merge(parent);
		assertEquals("merge() obviously did not work.", 3, mergedSet.size());
	}
