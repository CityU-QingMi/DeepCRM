	public void addQueryReturns(NativeSQLQueryReturn[] queryReturnsToAdd) {
		if ( queryReturnsToAdd != null && queryReturnsToAdd.length > 0 ) {
			int initialQueryReturnsLength = 0;
			if ( this.queryReturns != null ) {
				initialQueryReturnsLength = this.queryReturns.length;
			}
			NativeSQLQueryReturn[] allQueryReturns = new NativeSQLQueryReturn[initialQueryReturnsLength + queryReturnsToAdd.length];

			int i = 0;
			for ( i = 0; i < initialQueryReturnsLength; i++ ) {
				allQueryReturns[i] = this.queryReturns[i];
			}

			for ( int j = 0; j < queryReturnsToAdd.length; j++ ) {
				allQueryReturns[i] = queryReturnsToAdd[j];
				i++;
			}

			this.queryReturns = allQueryReturns;
		}
	}
