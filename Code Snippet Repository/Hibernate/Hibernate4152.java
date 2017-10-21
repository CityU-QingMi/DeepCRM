	public void processUpdateGeneratedProperties(
			Serializable id,
			Object entity,
			Object[] state,
			SharedSessionContractImplementor session) {
		if ( !hasUpdateGeneratedProperties() ) {
			throw new AssertionFailure( "no update-generated properties" );
		}
		processGeneratedProperties(
				id,
				entity,
				state,
				session,
				sqlUpdateGeneratedValuesSelectString,
				GenerationTiming.ALWAYS
		);
	}
