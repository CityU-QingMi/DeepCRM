	protected void doRetrieveMatchingServletContextResources(
			ServletContext servletContext, String fullPattern, String dir, Set<Resource> result)
			throws IOException {

		Set<String> candidates = servletContext.getResourcePaths(dir);
		if (candidates != null) {
			boolean dirDepthNotFixed = fullPattern.contains("**");
			int jarFileSep = fullPattern.indexOf(ResourceUtils.JAR_URL_SEPARATOR);
			String jarFilePath = null;
			String pathInJarFile = null;
			if (jarFileSep > 0 && jarFileSep + ResourceUtils.JAR_URL_SEPARATOR.length() < fullPattern.length()) {
				jarFilePath = fullPattern.substring(0, jarFileSep);
				pathInJarFile = fullPattern.substring(jarFileSep + ResourceUtils.JAR_URL_SEPARATOR.length());
			}
			for (String currPath : candidates) {
				if (!currPath.startsWith(dir)) {
					// Returned resource path does not start with relative directory:
					// assuming absolute path returned -> strip absolute path.
					int dirIndex = currPath.indexOf(dir);
					if (dirIndex != -1) {
						currPath = currPath.substring(dirIndex);
					}
				}
				if (currPath.endsWith("/") && (dirDepthNotFixed || StringUtils.countOccurrencesOf(currPath, "/") <=
						StringUtils.countOccurrencesOf(fullPattern, "/"))) {
					// Search subdirectories recursively: ServletContext.getResourcePaths
					// only returns entries for one directory level.
					doRetrieveMatchingServletContextResources(servletContext, fullPattern, currPath, result);
				}
				if (jarFilePath != null && getPathMatcher().match(jarFilePath, currPath)) {
					// Base pattern matches a jar file - search for matching entries within.
					String absoluteJarPath = servletContext.getRealPath(currPath);
					if (absoluteJarPath != null) {
						doRetrieveMatchingJarEntries(absoluteJarPath, pathInJarFile, result);
					}
				}
				if (getPathMatcher().match(fullPattern, currPath)) {
					result.add(new ServletContextResource(servletContext, currPath));
				}
			}
		}
	}
