	@Nullable
	protected String findWebJarResourcePath(String path) {
		try {
			int startOffset = (path.startsWith("/") ? 1 : 0);
			int endOffset = path.indexOf("/", 1);
			if (endOffset != -1) {
				String webjar = path.substring(startOffset, endOffset);
				String partialPath = path.substring(endOffset);
				String webJarPath = webJarAssetLocator.getFullPath(webjar, partialPath);
				return webJarPath.substring(WEBJARS_LOCATION_LENGTH);
			}
		}
		catch (MultipleMatchesException ex) {
			if (logger.isWarnEnabled()) {
				logger.warn("WebJar version conflict for \"" + path + "\"", ex);
			}
		}
		catch (IllegalArgumentException ex) {
			if (logger.isTraceEnabled()) {
				logger.trace("No WebJar resource found for \"" + path + "\"");
			}
		}
		return null;
	}
