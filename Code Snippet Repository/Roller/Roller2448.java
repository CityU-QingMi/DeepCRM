	private static void clearTomcatCache() {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		// no need for compilation here.
		Class cl = loader.getClass();

		try {
			if ("org.apache.catalina.loader.WebappClassLoader".equals(cl
					.getName())) {
				clearMap(cl, loader, "resourceEntries");
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("class loader " + cl.getName()
							+ " is not tomcat loader.");
				}
			}
		} catch (Exception e) {
			LOG.warn("couldn't clear tomcat cache", e);
		}
	}
