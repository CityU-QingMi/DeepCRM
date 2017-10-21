	@Override
	public void cancelLastQuery() {
		try {
			if (lastQuery != null) {
				lastQuery.cancel();
			}
		}
		catch (SQLException sqle) {
			throw exceptionHelper.convert( sqle, "Cannot cancel query" );
		}
		finally {
			lastQuery = null;
		}
	}
