	private List<Class<?>> findClassesForUri(URI baseUri, String basePackageName, ClassFilter classFilter) {
		try (CloseablePath closeablePath = CloseablePath.create(baseUri)) {
			Path baseDir = closeablePath.getPath();
			return findClassesForPath(baseDir, basePackageName, classFilter);
		}
		catch (PreconditionViolationException ex) {
			throw ex;
		}
		catch (Exception ex) {
			logger.warn(ex, () -> "Error scanning files for URI " + baseUri);
			return emptyList();
		}
	}
