	private static boolean deleteFiles(final File file) {
		boolean result = true;
		if (file.isDirectory()) {

			final File[] files = file.listFiles();
			if (files != null) {
				for (final File child : files) {
					result &= deleteFiles(child);
				}
			}

		} else if (!file.exists()) {
			return true;
		}

		return result && file.delete();
	}
