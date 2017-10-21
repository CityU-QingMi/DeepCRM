	public void apply(Project project) {
		project.getPlugins().apply( "java" );

		final HibernateExtension hibernateExtension = new HibernateExtension( project );

		project.getLogger().debug( "Adding Hibernate extensions to the build [{}]", project.getName() );
		project.getExtensions().add( "hibernate", hibernateExtension );

		project.afterEvaluate(
				new Action<Project>() {
					@Override
					public void execute(Project project) {
						if ( hibernateExtension.enhance != null ) {
							applyEnhancement( project, hibernateExtension );
						}
					}
				}
		);
	}
