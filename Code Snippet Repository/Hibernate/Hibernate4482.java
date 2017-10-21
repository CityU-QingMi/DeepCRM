	@Override
	@SuppressWarnings("")
	public <T> T unwrap(Class<T> cls) {
		if ( cls.isInstance( getProducer() ) ) {
			return (T) getProducer();
		}
		if ( cls.isInstance( getParameterMetadata() ) ) {
			return (T) getParameterMetadata();
		}
		if ( cls.isInstance( queryParameterBindings ) ) {
			return (T) queryParameterBindings;
		}
		if ( cls.isInstance( this ) ) {
			return (T) this;
		}

		throw new HibernateException( "Could not unwrap this [" + toString() + "] as requested Java type [" + cls.getName() + "]" );
//		throw new IllegalArgumentException( "Could not unwrap this [" + toString() + "] as requested Java type [" + cls.getName() + "]" );
	}
