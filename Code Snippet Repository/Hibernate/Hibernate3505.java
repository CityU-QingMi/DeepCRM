	private void advance(final ResultSet rs, final RowSelection selection) throws SQLException {

		final int firstRow = LimitHelper.getFirstRow( selection );
		if ( firstRow != 0 ) {
			if ( getFactory().getSessionFactoryOptions().isScrollableResultSetsEnabled() ) {
				// we can go straight to the first required row
				rs.absolute( firstRow );
			}
			else {
				// we need to step through the rows one row at a time (slow)
				for ( int m = 0; m < firstRow; m++ ) {
					rs.next();
				}
			}
		}
	}
