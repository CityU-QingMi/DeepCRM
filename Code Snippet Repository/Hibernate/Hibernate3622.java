	public ResultAliasContext process() {
		// first, break down the returns into maps keyed by alias
		// so that role returns can be more easily resolved to their owners
		for ( NativeSQLQueryReturn queryReturn : queryReturns ) {
			if ( queryReturn instanceof NativeSQLQueryNonScalarReturn ) {
				NativeSQLQueryNonScalarReturn rtn = (NativeSQLQueryNonScalarReturn) queryReturn;
				alias2Return.put( rtn.getAlias(), rtn );
				if ( rtn instanceof NativeSQLQueryJoinReturn ) {
					NativeSQLQueryJoinReturn fetchReturn = (NativeSQLQueryJoinReturn) rtn;
					alias2OwnerAlias.put( fetchReturn.getAlias(), fetchReturn.getOwnerAlias() );
				}
			}
		}

		// Now, process the returns
		for ( NativeSQLQueryReturn queryReturn : queryReturns ) {
			processReturn( queryReturn );
		}

		return new ResultAliasContext();
	}
