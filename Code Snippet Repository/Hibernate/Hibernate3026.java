	@Override
	public boolean previous() {
		if ( currentPosition <= 1 ) {
			currentPosition = 0;
			currentRow = null;
			return false;
		}

		final Object loadResult = getLoader().loadSequentialRowsReverse(
				getResultSet(),
				getSession(),
				getQueryParameters(),
				false,
				( maxPosition != null && currentPosition > maxPosition )
		);

		currentRow = new Object[] {loadResult};
		currentPosition--;

		afterScrollOperation();

		return true;
	}
