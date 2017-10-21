	protected ScrollMode getScrollMode(boolean scroll, boolean hasFirstRow, boolean useLimitOffSet, QueryParameters queryParameters) {
		final boolean canScroll = getFactory().getSettings().isScrollableResultSetsEnabled();
		if ( canScroll ) {
			if ( scroll ) {
				return queryParameters.getScrollMode();
			}
			if ( hasFirstRow && !useLimitOffSet ) {
				return ScrollMode.SCROLL_INSENSITIVE;
			}
		}
		return null;
	}
