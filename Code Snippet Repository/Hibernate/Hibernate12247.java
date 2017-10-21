	public static Class<?> getMetamodelClassFor(Class<?> entityClass) {
		assertNotNull( "Class parameter cannot be null", entityClass );
		String metaModelClassName = entityClass.getName() + META_MODEL_CLASS_POSTFIX;
		try {
			URL outDirUrl = OUT_BASE_DIR.toURI().toURL();
			URL[] urls = new URL[1];
			urls[0] = outDirUrl;
			URLClassLoader classLoader = new URLClassLoader( urls, TestUtil.class.getClassLoader() );
			return classLoader.loadClass( metaModelClassName );
		}
		catch ( Exception e ) {
			fail( metaModelClassName + " was not generated." );
		}
		// keep the compiler happy
		return null;
	}
