	public long getLastModified(Resource resource) {

		String rootPath = servletContext.getRealPath("/");
		if (rootPath == null) {
			// RootPath is null if the servlet container cannot translate the
			// virtual path to a real path for any reason (such as when the
			// content is being made available from a .war archive)
			return 0;
		}

		File cachedFile = getCachedFile(rootPath, resource.getName());
		if (cachedFile.canRead()) {
			return cachedFile.lastModified();
		} else {
			return 0;
		}

	}
