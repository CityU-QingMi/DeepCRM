	@Override
	public boolean equals(Object other) {
		if ( !(other instanceof FilterKey) ) {
			return false;
		}

		final FilterKey that = (FilterKey) other;
		return that.filterName.equals( filterName )
				&& that.filterParameters.equals( filterParameters );
	}
