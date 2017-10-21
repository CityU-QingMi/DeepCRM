	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof MultipleCollectionRefEntity2) ) {
			return false;
		}

		MultipleCollectionRefEntity2 that = (MultipleCollectionRefEntity2) o;

		if ( text != null ? !text.equals( that.text ) : that.text != null ) {
			return false;
		}

		return true;
	}
