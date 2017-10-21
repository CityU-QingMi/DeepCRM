	private static int shallowIndexOfPattern(final StringBuilder sb, final Pattern pattern, int fromIndex) {
		int index = -1;
		final String matchString = sb.toString();

		// quick exit, save performance and avoid exceptions
		if ( matchString.length() < fromIndex || fromIndex < 0 ) {
			return -1;
		}

		List<IgnoreRange> ignoreRangeList = generateIgnoreRanges( matchString );

		Matcher matcher = pattern.matcher( matchString );
		matcher.region( fromIndex, matchString.length() );

		if ( ignoreRangeList.isEmpty() ) {
			// old behavior where the first match is used if no ignorable ranges
			// were deduced from the matchString.
			if ( matcher.find() && matcher.groupCount() > 0 ) {
				index = matcher.start();
			}
		}
		else {
			// rather than taking the first match, we now iterate all matches
			// until we determine a match that isn't considered "ignorable'.
			while ( matcher.find() && matcher.groupCount() > 0 ) {
				final int position = matcher.start();
				if ( !isPositionIgnorable( ignoreRangeList, position ) ) {
					index = position;
					break;
				}
			}
		}
		return index;
	}
