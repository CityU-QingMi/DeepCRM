	private URLClassLoader getURLClassLoader() {
		URLClassLoader ucl = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		if(! (loader instanceof URLClassLoader)) {
			loader = ClassPathFinder.class.getClassLoader();
			if (loader instanceof URLClassLoader) {
				ucl = (URLClassLoader) loader ;
			}
		}
		else {
			ucl = (URLClassLoader) loader;
		}
		
		return ucl ;
	}
