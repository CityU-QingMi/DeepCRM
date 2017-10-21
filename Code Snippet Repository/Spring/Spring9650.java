	private void doRetrieveMatchingJarEntries(String jarFilePath, String entryPattern, Set<Resource> result) {
		if (logger.isDebugEnabled()) {
			logger.debug("Searching jar file [" + jarFilePath + "] for entries matching [" + entryPattern + "]");
		}
		try {
			JarFile jarFile = new JarFile(jarFilePath);
			try {
				for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements();) {
					JarEntry entry = entries.nextElement();
					String entryPath = entry.getName();
					if (getPathMatcher().match(entryPattern, entryPath)) {
						result.add(new UrlResource(
								ResourceUtils.URL_PROTOCOL_JAR,
								ResourceUtils.FILE_URL_PREFIX + jarFilePath + ResourceUtils.JAR_URL_SEPARATOR + entryPath));
					}
				}
			}
			finally {
				jarFile.close();
			}
		}
		catch (IOException ex) {
			if (logger.isWarnEnabled()) {
				logger.warn("Cannot search for matching resources in jar file [" + jarFilePath +
						"] because the jar cannot be opened through the file system", ex);
			}
		}
	}
