	public boolean isSourceModified(Resource resource) {

		String rootPath = servletContext.getRealPath("/");
		if (rootPath == null) {
			// RootPath is null if the servlet container cannot translate the
			// virtual path to a real path for any reason (such as when the
			// content is being made available from a .war archive)
			return false;
		}

		// first, try getting the previously found file
		String fileName = resource.getName();
		File cachedFile = getCachedFile(rootPath, fileName);
		if (!cachedFile.exists()) {
			// then the source has been moved and/or deleted
			return true;
		}

/**/
/**/
/**/
/**/
		File currentFile = null;
		for (String path : paths) {
			currentFile = new File(rootPath + path, fileName);
			if (currentFile.canRead()) {
/**/
/**/
/**/
/**/
				break;
			}
		}

		// If the current is the cached and it is readable
		if (cachedFile.equals(currentFile) && cachedFile.canRead()) {
			// then (and only then) do we compare the last modified values
			return (cachedFile.lastModified() != resource.getLastModified());
		} else {
			// We found a new file for the resource or the resource is no longer
			// readable.
			return true;
		}
	}
