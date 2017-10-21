	private void addSqlResultsetMappingIfNeeded(SqlResultSetMapping annotation, List<SqlResultSetMapping> resultsets) {
		if ( annotation != null ) {
			String resultsetName = annotation.name();
			boolean present = false;
			for ( SqlResultSetMapping current : resultsets ) {
				if ( current.name().equals( resultsetName ) ) {
					present = true;
					break;
				}
			}
			if ( !present ) {
				resultsets.add( annotation );
			}
		}
	}
