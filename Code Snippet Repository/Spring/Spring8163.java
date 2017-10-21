		@Override
		protected boolean removeEldestEntry(Map.Entry<MergedContextConfiguration, ApplicationContext> eldest) {
			if (this.size() > DefaultContextCache.this.getMaxSize()) {
				// Do NOT delete "DefaultContextCache.this."; otherwise, we accidentally
				// invoke java.util.Map.remove(Object, Object).
				DefaultContextCache.this.remove(eldest.getKey(), HierarchyMode.CURRENT_LEVEL);
			}

			// Return false since we invoke a custom eviction algorithm.
			return false;
		}
