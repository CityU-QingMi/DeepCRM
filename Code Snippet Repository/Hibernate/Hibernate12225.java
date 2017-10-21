	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		HashEntity that = (HashEntity) o;

		if ( id != that.id ) {
			return false;
		}

		return true;
	}
