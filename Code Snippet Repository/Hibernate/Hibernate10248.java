	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof MultipleCollectionEntity) ) {
			return false;
		}

		MultipleCollectionEntity that = (MultipleCollectionEntity) o;

		if ( refEntities1 != null ? !refEntities1.equals( that.refEntities1 ) : that.refEntities1 != null ) {
			return false;
		}
		if ( refEntities2 != null ? !refEntities2.equals( that.refEntities2 ) : that.refEntities2 != null ) {
			return false;
		}
		if ( text != null ? !text.equals( that.text ) : that.text != null ) {
			return false;
		}

		return true;
	}
