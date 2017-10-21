		@Override
		protected String doExtractConstraintName(SQLException sqle) throws NumberFormatException {
			final int sqlState = Integer.valueOf( JdbcExceptionHelper.extractSqlState( sqle ) ).intValue();
			switch ( sqlState ) {
			case 23000:
				return extractUsingTemplate( " for key '", "'", sqle.getMessage() );
			default:
				return null;
			}
		}
