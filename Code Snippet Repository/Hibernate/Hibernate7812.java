	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Category ) ) return false;

		final Category category = ( Category ) o;

		if ( !name.equals( category.name ) ) {
			return false;
		}

		if ( effectiveEndDate != null ?
		        !effectiveEndDate.equals( category.effectiveEndDate ) :
		        category.effectiveEndDate != null ) {
			return false;
		}

		if ( effectiveStartDate != null ?
		        !effectiveStartDate.equals( category.effectiveStartDate ) :
		        category.effectiveStartDate != null ) {
			return false;
		}

		return true;
	}
