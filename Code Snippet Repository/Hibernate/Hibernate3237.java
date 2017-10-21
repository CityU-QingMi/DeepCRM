	public static StringBuilder buildBatchFetchRestrictionFragment(
			String alias,
			String[] columnNames,
			Dialect dialect) {
		// the general idea here is to just insert a placeholder that we can easily find later...
		if ( columnNames.length == 1 ) {
			// non-composite key
			return new StringBuilder( StringHelper.qualify( alias, columnNames[0] ) )
					.append( " in (" ).append( BATCH_ID_PLACEHOLDER ).append( ")" );
		}
		else {
			// composite key - the form to use here depends on what the dialect supports.
			if ( dialect.supportsRowValueConstructorSyntaxInInList() ) {
				// use : (col1, col2) in ( (?,?), (?,?), ... )
				StringBuilder builder = new StringBuilder();
				builder.append( "(" );
				boolean firstPass = true;
				String deliminator = "";
				for ( String columnName : columnNames ) {
					builder.append( deliminator ).append( StringHelper.qualify( alias, columnName ) );
					if ( firstPass ) {
						firstPass = false;
						deliminator = ",";
					}
				}
				builder.append( ") in (" );
				builder.append( BATCH_ID_PLACEHOLDER );
				builder.append( ")" );
				return builder;
			}
			else {
				// use : ( (col1 = ? and col2 = ?) or (col1 = ? and col2 = ?) or ... )
				//		unfortunately most of this building needs to be held off until we know
				//		the exact number of ids :(
				return new StringBuilder( "(" ).append( BATCH_ID_PLACEHOLDER ).append( ")" );
			}
		}
	}
