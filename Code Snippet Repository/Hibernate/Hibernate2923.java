	public LegacyHiLoAlgorithmOptimizer(Class returnClass, int incrementSize) {
		super( returnClass, incrementSize );
		if ( incrementSize < 1 ) {
			throw new HibernateException( "increment size cannot be less than 1" );
		}
		if ( log.isTraceEnabled() ) {
			log.tracev( "Creating hilo optimizer (legacy) with [incrementSize={0}; returnClass={1}]", incrementSize, returnClass.getName() );
		}
		initialMaxLo = incrementSize;
	}
