	public PooledOptimizer(Class returnClass, int incrementSize) {
		super( returnClass, incrementSize );
		if ( incrementSize < 1 ) {
			throw new HibernateException( "increment size cannot be less than 1" );
		}
		if ( log.isTraceEnabled() ) {
			log.tracev(
					"Creating pooled optimizer with [incrementSize={0}; returnClass={1}]",
					incrementSize,
					returnClass.getName()
			);
		}
	}
