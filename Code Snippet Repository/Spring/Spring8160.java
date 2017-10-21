	@Override
	public void remove(MergedContextConfiguration key, @Nullable HierarchyMode hierarchyMode) {
		Assert.notNull(key, "Key must not be null");

		// startKey is the level at which to begin clearing the cache,
		// depending on the configured hierarchy mode.s
		MergedContextConfiguration startKey = key;
		if (hierarchyMode == HierarchyMode.EXHAUSTIVE) {
			MergedContextConfiguration parent = startKey.getParent();
			while (parent != null) {
				startKey = parent;
				parent = startKey.getParent();
			}
		}

		List<MergedContextConfiguration> removedContexts = new ArrayList<>();
		remove(removedContexts, startKey);

		// Remove all remaining references to any removed contexts from the
		// hierarchy map.
		for (MergedContextConfiguration currentKey : removedContexts) {
			for (Set<MergedContextConfiguration> children : this.hierarchyMap.values()) {
				children.remove(currentKey);
			}
		}

		// Remove empty entries from the hierarchy map.
		for (MergedContextConfiguration currentKey : this.hierarchyMap.keySet()) {
			if (this.hierarchyMap.get(currentKey).isEmpty()) {
				this.hierarchyMap.remove(currentKey);
			}
		}
	}
