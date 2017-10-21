	@Override
	public int[] sqlTypes(Mapping mapping) throws MappingException {
		//Not called at runtime so doesn't matter if its slow :)
		int[] sqlTypes = new int[getColumnSpan( mapping )];
		int n = 0;
		for ( int i = 0; i < propertySpan; i++ ) {
			int[] subtypes = propertyTypes[i].sqlTypes( mapping );
			for ( int subtype : subtypes ) {
				sqlTypes[n++] = subtype;
			}
		}
		return sqlTypes;
	}
