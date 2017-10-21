	private void remove(List<MergedContextConfiguration> removedContexts, MergedContextConfiguration key) {
		Assert.notNull(key, "Key must not be null");

		Set<MergedContextConfiguration> children = this.hierarchyMap.get(key);
		if (children != null) {
			for (MergedContextConfiguration child : children) {
				// Recurse through lower levels
				remove(removedContexts, child);
			}
			// Remove the set of children for the current context from the hierarchy map.
			this.hierarchyMap.remove(key);
		}

		// Physically remove and close leaf nodes first (i.e., on the way back up the
		// stack as opposed to prior to the recursive call).
		ApplicationContext context = this.contextMap.remove(key);
		if (context instanceof ConfigurableApplicationContext) {
			((ConfigurableApplicationContext) context).close();
		}
		removedContexts.add(key);
	}
