	private List<URI> getRootUrisForPackage(String basePackageName) {
		try {
			Enumeration<URL> resources = getClassLoader().getResources(packagePath(basePackageName));
			List<URI> uris = new ArrayList<>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				uris.add(resource.toURI());
			}
			return uris;
		}
		catch (Exception ex) {
			logger.warn(ex, () -> "Error reading URIs from class loader for base package " + basePackageName);
			return emptyList();
		}
	}
