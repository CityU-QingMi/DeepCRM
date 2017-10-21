	public void processInsertGeneratedProperties(
			Serializable id,
			Object entity,
			Object[] state,
			SharedSessionContractImplementor session) {
		if ( !hasInsertGeneratedProperties() ) {
			throw new AssertionFailure( "no insert-generated properties" );
		}
		processGeneratedProperties(
				id,
				entity,
				state,
				session,
				sqlInsertGeneratedValuesSelectString,
				GenerationTiming.INSERT
		);
	}
