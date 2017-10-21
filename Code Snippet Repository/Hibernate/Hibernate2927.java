	@Deprecated
	public static Optimizer buildOptimizer(String type, Class returnClass, int incrementSize) {
		final Class<? extends Optimizer> optimizerClass;

		final StandardOptimizerDescriptor standardDescriptor = StandardOptimizerDescriptor.fromExternalName( type );
		if ( standardDescriptor != null ) {
			optimizerClass = standardDescriptor.getOptimizerClass();
		}
		else {
			try {
				optimizerClass = ReflectHelper.classForName( type );
			}
			catch( Throwable ignore ) {
				LOG.unableToLocateCustomOptimizerClass( type );
				return buildFallbackOptimizer( returnClass, incrementSize );
			}
		}

		try {
			final Constructor ctor = optimizerClass.getConstructor( CTOR_SIG );
			return (Optimizer) ctor.newInstance( returnClass, incrementSize );
		}
		catch( Throwable ignore ) {
			LOG.unableToInstantiateOptimizer( type );
		}

		return buildFallbackOptimizer( returnClass, incrementSize );
	}
