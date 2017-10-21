	private void processReturn(NativeSQLQueryReturn rtn) {
		if ( rtn instanceof NativeSQLQueryScalarReturn ) {
			processScalarReturn( ( NativeSQLQueryScalarReturn ) rtn );
		}
		else if ( rtn instanceof NativeSQLQueryRootReturn ) {
			processRootReturn( ( NativeSQLQueryRootReturn ) rtn );
		}
		else if ( rtn instanceof NativeSQLQueryCollectionReturn ) {
			processCollectionReturn( (NativeSQLQueryCollectionReturn) rtn );
		}
		else if ( NativeSQLQueryJoinReturn.class.isInstance( rtn ) ) {
			processJoinReturn( ( NativeSQLQueryJoinReturn ) rtn );
		}
		else if ( NativeSQLQueryConstructorReturn.class.isInstance(  rtn ) ) {
			processConstructorReturn( (NativeSQLQueryConstructorReturn) rtn );
		}
		else {
			throw new IllegalStateException(
					"Unrecognized NativeSQLQueryReturn concrete type encountered : " + rtn
			);
		}
	}
