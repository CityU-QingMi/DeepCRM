					@Override
					protected boolean removeEldestEntry(Map.Entry<String, LinkedMultiValueMap<String, String>> eldest) {
						if (size() > getCacheLimit()) {
							accessCache.remove(eldest.getKey());
							return true;
						}
						else {
							return false;
						}
					}
