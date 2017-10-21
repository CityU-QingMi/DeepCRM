	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof Age ) ) {
			return false;
		}

		Age age = (Age) o;

		if ( ageInYears != age.ageInYears ) {
			return false;
		}

		return true;
	}
