	@Override
	public boolean equals(Object other) {
		if ( !( other instanceof QueryKey ) ) {
			return false;
		}

		final QueryKey that = (QueryKey) other;
		if ( !sqlQueryString.equals( that.sqlQueryString ) ) {
			return false;
		}
		if ( !EqualsHelper.equals( firstRow, that.firstRow ) || !EqualsHelper.equals( maxRows, that.maxRows ) ) {
			return false;
		}
		if ( !EqualsHelper.equals( customTransformer, that.customTransformer ) ) {
			return false;
		}
		if ( positionalParameterTypes == null ) {
			if ( that.positionalParameterTypes != null ) {
				return false;
			}
		}
		else {
			if ( that.positionalParameterTypes == null ) {
				return false;
			}
			if ( positionalParameterTypes.length != that.positionalParameterTypes.length ) {
				return false;
			}
			for ( int i = 0; i < positionalParameterTypes.length; i++ ) {
				if ( positionalParameterTypes[i].getReturnedClass() != that.positionalParameterTypes[i].getReturnedClass() ) {
					return false;
				}
				if ( !positionalParameterTypes[i].isEqual( positionalParameterValues[i], that.positionalParameterValues[i] ) ) {
					return false;
				}
			}
		}

		return EqualsHelper.equals( filterKeys, that.filterKeys )
				&& EqualsHelper.equals( namedParameters, that.namedParameters )
				&& EqualsHelper.equals( tenantIdentifier, that.tenantIdentifier );
	}
