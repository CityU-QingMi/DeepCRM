	private ClassLoader toClassLoader(List<File> runtimeClasspath) throws MojoExecutionException {
		List<URL> urls = new ArrayList<URL>();
		for ( File file : runtimeClasspath ) {
			try {
				urls.add( file.toURI().toURL() );
				getLog().debug( "Adding classpath entry for classes root " + file.getAbsolutePath() );
			}
			catch (MalformedURLException e) {
				String msg = "Unable to resolve classpath entry to URL: " + file.getAbsolutePath();
				if ( failOnError ) {
					throw new MojoExecutionException( msg, e );
				}
				getLog().warn( msg );
			}
		}

		// HHH-10145 Add dependencies to classpath as well - all but the ones used for testing purposes
		MavenProject project = ( (MavenProject) getPluginContext().get( "project" ) );
		Set<Artifact> artifacts = project.getArtifacts();
		if ( artifacts != null) {
			for ( Artifact a : artifacts ) {
				if ( !Artifact.SCOPE_TEST.equals( a.getScope() ) ) {
					try {
						urls.add( a.getFile().toURI().toURL() );
						getLog().debug( "Adding classpath entry for dependency " + a.getId() );
					}
					catch (MalformedURLException e) {
						String msg = "Unable to resolve URL for dependency " + a.getId() + " at " + a.getFile().getAbsolutePath();
						if ( failOnError ) {
							throw new MojoExecutionException( msg, e );
						}
						getLog().warn( msg );
					}
				}
			}
		}

		return new URLClassLoader( urls.toArray( new URL[urls.size()] ), Enhancer.class.getClassLoader() );
	}
