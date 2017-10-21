		public String[] toColumns(String propertyName) throws QueryException, UnsupportedOperationException {
			if ( "sql".equals( propertyName ) ) {
				return new String[] { "sql" };
			}
			else if ( "component".equals( propertyName ) ) {
				return new String[] { "comp_1", "comp_2" };
			}
			else if ( "component.prop1".equals( propertyName ) ) {
				return new String[] { "comp_1" };
			}
			else if ( "component.prop2".equals( propertyName ) ) {
				return new String[] { "comp_2" };
			}
			else if ( "property".equals( propertyName ) ) {
				return new String[] { "prop" };
			}
			throw new QueryException( "could not resolve property: " + propertyName );
		}
