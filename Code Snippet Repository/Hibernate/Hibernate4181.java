	public String[] toColumns(String alias, String propertyName) throws QueryException {
		if ( ENTITY_CLASS.equals( propertyName ) ) {
			if ( explicitDiscriminatorColumnName == null ) {
				return new String[] { discriminatorFragment( alias ).toFragmentString() };
			}
			else {
				return new String[] { StringHelper.qualify( alias, explicitDiscriminatorColumnName ) };
			}
		}
		else {
			return super.toColumns( alias, propertyName );
		}
	}
