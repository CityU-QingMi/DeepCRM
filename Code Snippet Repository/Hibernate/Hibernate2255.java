	protected NativeSQLQueryNonScalarReturn(String alias, Map<String,String[]> propertyResults, LockMode lockMode) {
		this.alias = alias;
		if ( alias == null ) {
			throw new HibernateException("alias must be specified");
		}
		this.lockMode = lockMode;
		if ( propertyResults != null ) {
			this.propertyResults.putAll( propertyResults );
		}
		this.hashCode = determineHashCode();
	}
