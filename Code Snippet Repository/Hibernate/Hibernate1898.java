	private static List<IgnoreRange> generateIgnoreRanges(String sql) {
		List<IgnoreRange> ignoreRangeList = new ArrayList<IgnoreRange>();

		int depth = 0;
		int start = -1;
		boolean insideAStringValue = false;
		for ( int i = 0; i < sql.length(); ++i ) {
			final char ch = sql.charAt( i );
			if ( ch == '\'' ) {
				insideAStringValue = !insideAStringValue;
			}
			else if ( ch == '(' && !insideAStringValue ) {
				depth++;
				if ( depth == 1 ) {
					start = i;
				}
			}
			else if ( ch == ')' && !insideAStringValue ) {
				if ( depth > 0 ) {
					if ( depth == 1 ) {
						ignoreRangeList.add( new IgnoreRange( start, i ) );
						start = -1;
					}
					depth--;
				}
				else {
					throw new IllegalStateException( "Found an unmatched ')' at position " + i + ": " + sql );
				}
			}
		}

		if ( depth != 0 ) {
			throw new IllegalStateException( "Unmatched parenthesis in rendered SQL (" + depth + " depth): " + sql );
		}

		return ignoreRangeList;
	}
