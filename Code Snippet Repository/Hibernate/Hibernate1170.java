	private int generateHashCode() {
		int result = 13;
		result = 37 * result + ( firstRow==null ? 0 : firstRow.hashCode() );
		result = 37 * result + ( maxRows==null ? 0 : maxRows.hashCode() );
		for ( int i=0; i< positionalParameterValues.length; i++ ) {
			result = 37 * result + ( positionalParameterValues[i]==null ? 0 : positionalParameterTypes[i].getHashCode( positionalParameterValues[i] ) );
		}
		result = 37 * result + ( namedParameters==null ? 0 : namedParameters.hashCode() );
		result = 37 * result + ( filterKeys ==null ? 0 : filterKeys.hashCode() );
		result = 37 * result + ( customTransformer==null ? 0 : customTransformer.hashCode() );
		result = 37 * result + ( tenantIdentifier==null ? 0 : tenantIdentifier.hashCode() );
		result = 37 * result + sqlQueryString.hashCode();
		return result;
	}
