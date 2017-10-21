	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final BasicBatchKey that = (BasicBatchKey) o;
		return comparison.equals( that.comparison );
	}
