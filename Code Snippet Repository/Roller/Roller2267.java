	private File getCachedFile(String rootPath, String fileName) {

		// We do this when we cache a resource, so do it again to ensure a match
		while (fileName.startsWith("/")) {
			fileName = fileName.substring(1);
		}

		String savedPath = templatePaths.get(fileName);

		// names are <template>|<deviceType>
		// loading weblog.vm etc will not have the type so only check for
		// one.
		String[] split = fileName.split("\\|", 2);
		return new File(rootPath + savedPath, split[0]);

	}
