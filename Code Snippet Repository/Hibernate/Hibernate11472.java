	public static void removeAll(AdvancedCache cache) {
		CloseableIterator it = cache.keySet().iterator();
		try {
			while (it.hasNext()) {
				// Cannot use it.next(); it.remove() due to ISPN-5653
				cache.remove(it.next());
			}
		}
		finally {
			it.close();
		}
	}
