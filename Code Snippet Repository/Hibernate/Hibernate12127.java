	private ClassLoader toClassLoader(FileCollection runtimeClasspath) {
		List<URL> urls = new ArrayList<URL>();
		for ( File file : runtimeClasspath ) {
			try {
				urls.add( file.toURI().toURL() );
				logger.debug( "Adding classpath entry for " + file.getAbsolutePath() );
			}
			catch (MalformedURLException e) {
				throw new GradleException( "Unable to resolve classpath entry to URL : " + file.getAbsolutePath(), e );
			}
		}
		return new URLClassLoader( urls.toArray( new URL[urls.size()] ), Enhancer.class.getClassLoader() );
	}
