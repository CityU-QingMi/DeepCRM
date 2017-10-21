	@SuppressWarnings("")
	public void sort() {
		if ( sorted || !requiresSorting ) {
			// nothing to do
			return;
		}

		if ( sorter != null ) {
			sorter.sort( executables );
		}
		else {
			Collections.sort( executables );
		}
		sorted = true;
	}
