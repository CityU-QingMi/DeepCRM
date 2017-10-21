		@Override
		protected String doExtractConstraintName(SQLException sqle) throws NumberFormatException {
			String constraintName = null;

			int errorCode = sqle.getErrorCode();
			if ( errorCode == 27003 ) {
				constraintName = extractUsingTemplate( "Unique constraint (", ") violated.", sqle.getMessage() );
			}
			else if ( errorCode == 2700 ) {
				constraintName = extractUsingTemplate( "Referential constraint", "violation:", sqle.getMessage() );
			}
			else if ( errorCode == 5317 ) {
				constraintName = extractUsingTemplate( "Check constraint (", ") violated.", sqle.getMessage() );
			}

			if ( constraintName != null ) {
				int i = constraintName.indexOf( '.' );
				if ( i != -1 ) {
					constraintName = constraintName.substring( i + 1 );
				}
			}
			return constraintName;
		}
