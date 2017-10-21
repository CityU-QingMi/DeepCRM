	private void populateLocalCache() {
		CloseableIterator iterator = cache.keySet().iterator();
		try {
			while (iterator.hasNext()) {
				get(null, iterator.next());
			}
		}
		finally {
			iterator.close();
		}
	}
