	private SessionFactoryImplementor buildSessionFactory(List<Class<?>> classesUnderTest, List<String> configFiles) {
		assert classesUnderTest != null;
		assert configFiles != null;
		Configuration cfg = new Configuration();
		for ( Class<?> clazz : classesUnderTest ) {
			cfg.addAnnotatedClass( clazz );
		}
		for ( String configFile : configFiles ) {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream( configFile );
			cfg.addInputStream( is );
		}
		return ( SessionFactoryImplementor ) cfg.buildSessionFactory();
	}
