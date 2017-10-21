	@Override
	public int[] sqlTypes(Mapping mapping) throws MappingException {
		int[] result = new int[getColumnSpan( mapping )];
		int n = 0;
		for ( Type type : userType.getPropertyTypes() ) {
			for ( int sqlType : type.sqlTypes( mapping ) ) {
				result[n++] = sqlType;
			}
		}
		return result;
	}
