	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !(obj instanceof EmbIdWithCustomType) ) {
			return false;
		}

		EmbIdWithCustomType that = (EmbIdWithCustomType) obj;

		if ( x != null ? !x.equals( that.x ) : that.x != null ) {
			return false;
		}
		if ( customEnum != that.customEnum ) {
			return false;
		}

		return true;
	}
