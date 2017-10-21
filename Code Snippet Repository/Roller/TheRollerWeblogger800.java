	public static void reloadBundle(Locale key) {

		try {

			Class type = ResourceBundle.class;
			Field cacheList = type.getDeclaredField("cacheList");

			synchronized (cacheList) {
				cacheList.setAccessible(true);
				((Map) cacheList.get(ResourceBundle.class)).clear();
			}

			clearTomcatCache();

			// Remove cached bundle
			messagesMap.remove(key);

		} catch (Exception e) {
			LOG.error("Error clearing message resource bundles", e);
		}

	}
