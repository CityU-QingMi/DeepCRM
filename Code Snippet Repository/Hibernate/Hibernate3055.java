	private void prepareCurrentRow(boolean underlyingScrollSuccessful) {
		if ( !underlyingScrollSuccessful ) {
			currentRow = null;
			return;
		}

		final Object result = getLoader().loadSingleRow(
				getResultSet(),
				getSession(),
				getQueryParameters(),
				true
		);
		if ( result != null && result.getClass().isArray() ) {
			currentRow = (Object[]) result;
		}
		else {
			currentRow = new Object[] {result};
		}

		if ( getHolderInstantiator() != null ) {
			currentRow = new Object[] {getHolderInstantiator().instantiate( currentRow )};
		}

		afterScrollOperation();
	}
