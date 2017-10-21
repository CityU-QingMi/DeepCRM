	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof SortedSetEntity) ) {
			return false;
		}

		SortedSetEntity that = (SortedSetEntity) o;

		return !(data != null ? !data.equals( that.data ) : that.data != null) && !(id != null ?
				!id.equals( that.id ) :
				that.id != null);
	}
