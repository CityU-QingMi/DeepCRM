	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		FileTimeStampChecker that = (FileTimeStampChecker) o;

		if ( !lastModifiedCache.equals( that.lastModifiedCache ) ) {
			return false;
		}

		return true;
	}
