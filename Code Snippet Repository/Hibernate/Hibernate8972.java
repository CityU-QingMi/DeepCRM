	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		SubCategory item = (SubCategory) o;

		if ( !name.equals( item.name ) ) {
			return false;
		}

		return true;
	}
