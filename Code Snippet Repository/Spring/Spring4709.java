	public static boolean isCacheSafe(Class<?> clazz, @Nullable ClassLoader classLoader) {
		Assert.notNull(clazz, "Class must not be null");
		try {
			ClassLoader target = clazz.getClassLoader();
			if (target == null) {
				return true;
			}
			ClassLoader cur = classLoader;
			if (cur == target) {
				return true;
			}
			while (cur != null) {
				cur = cur.getParent();
				if (cur == target) {
					return true;
				}
			}
			return false;
		}
		catch (SecurityException ex) {
			// Probably from the system ClassLoader - let's consider it safe.
			return true;
		}
	}
