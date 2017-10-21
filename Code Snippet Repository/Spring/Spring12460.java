	protected ClassLoader createTemplateClassLoader() throws IOException {
		String[] paths = StringUtils.commaDelimitedListToStringArray(getResourceLoaderPath());
		List<URL> urls = new ArrayList<>();
		for (String path : paths) {
			Resource[] resources = getApplicationContext().getResources(path);
			if (resources.length > 0) {
				for (Resource resource : resources) {
					if (resource.exists()) {
						urls.add(resource.getURL());
					}
				}
			}
		}
		ClassLoader classLoader = getApplicationContext().getClassLoader();
		Assert.state(classLoader != null, "No ClassLoader");
		return (urls.size() > 0 ? new URLClassLoader(urls.toArray(new URL[urls.size()]), classLoader) : classLoader);
	}
