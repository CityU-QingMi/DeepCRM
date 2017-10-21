	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof MultipleCollectionRefEntity1) ) {
			return false;
		}

		MultipleCollectionRefEntity1 that = (MultipleCollectionRefEntity1) o;

		if ( text != null ? !text.equals( that.text ) : that.text != null ) {
			return false;
		}

		return true;
	}
