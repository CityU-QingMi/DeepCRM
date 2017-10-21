	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof WikiImage) ) {
			return false;
		}

		WikiImage wikiImage = (WikiImage) o;

		if ( name != null ? !name.equals( wikiImage.name ) : wikiImage.name != null ) {
			return false;
		}

		return true;
	}
