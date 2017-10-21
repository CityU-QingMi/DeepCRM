	private ClassLoader toClassLoader(List<File> runtimeClasspath) throws BuildException {
		List<URL> urls = new ArrayList<>();
		for ( File file : runtimeClasspath ) {
			try {
				urls.add( file.toURI().toURL() );
				log( "Adding classpath entry for classes root " + file.getAbsolutePath(), Project.MSG_DEBUG );
			}
			catch ( MalformedURLException e ) {
				String msg = "Unable to resolve classpath entry to URL: " + file.getAbsolutePath();
				if ( failOnError ) {
					throw new BuildException( msg, e );
				}
				log( msg, Project.MSG_WARN );
			}
		}

		return new URLClassLoader( urls.toArray( new URL[urls.size()] ), Enhancer.class.getClassLoader() );
	}
