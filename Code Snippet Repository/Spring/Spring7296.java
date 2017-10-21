				@Override
				protected boolean removeEldestEntry(Map.Entry<String, byte[]> eldest) {
					if (size() > HEADER_KEY_CACHE_LIMIT) {
						headerKeyAccessCache.remove(eldest.getKey());
						return true;
					}
					else {
						return false;
					}
				}
