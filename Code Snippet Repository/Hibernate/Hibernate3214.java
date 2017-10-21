	public static Object getConstantValue(String name, SessionFactoryImplementor factory) {
		boolean conventionalJavaConstants = factory.getSessionFactoryOptions().isConventionalJavaConstants();
		Class clazz;
		try {
			if ( conventionalJavaConstants &&
				!JAVA_CONSTANT_PATTERN.matcher( name ).find() ) {
				return null;
			}
			ClassLoaderService classLoaderService = factory.getServiceRegistry().getService( ClassLoaderService.class );
			clazz = classLoaderService.classForName( StringHelper.qualifier( name ) );
		}
		catch ( Throwable t ) {
			return null;
		}
		try {
			return clazz.getField( StringHelper.unqualify( name ) ).get( null );
		}
		catch ( Throwable t ) {
			return null;
		}
	}
