		@SuppressWarnings("")
		public Map<String, String> getMap(Object target) {
			try {
				Field f = target.getClass().getDeclaredField(mapName);
				return (Map<String, String>) f.get(target);
			}
			catch (Exception ex) {
			}
			return null;
		}
