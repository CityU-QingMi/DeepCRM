	public NativeSQLQuerySpecification(
			String queryString,
			NativeSQLQueryReturn[] queryReturns,
			Collection querySpaces) {
		this.queryString = queryString;
		this.queryReturns = queryReturns;
		if ( querySpaces == null ) {
			this.querySpaces = Collections.EMPTY_SET;
		}
		else {
			Set tmp = new HashSet();
			tmp.addAll( querySpaces );
			this.querySpaces = Collections.unmodifiableSet( tmp );
		}

		// pre-determine and cache the hashcode
		int hashCode = queryString.hashCode();
		hashCode = 29 * hashCode + this.querySpaces.hashCode();
		if ( this.queryReturns != null ) {
			hashCode = 29 * hashCode + ArrayHelper.toList( this.queryReturns ).hashCode();
		}
		this.hashCode = hashCode;
	}
