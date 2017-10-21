	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure( type, params, serviceRegistry );

		maxLo = ConfigurationHelper.getInt( MAX_LO, params, 9 );

		if ( maxLo >= 1 ) {
			hiloOptimizer = new LegacyHiLoAlgorithmOptimizer(
					getIdentifierType().getReturnedClass(),
					maxLo
			);
		}
	}
