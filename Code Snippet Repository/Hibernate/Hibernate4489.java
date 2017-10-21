	@Override
	public ScrollableResultsImplementor scroll(ScrollMode scrollMode) {
		beforeQuery();
		try {
			return doScroll( scrollMode );
		}
		finally {
			afterQuery();
		}
	}
