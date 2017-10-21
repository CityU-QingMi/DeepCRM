	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof PropertyNotUpdatableEntity) ) {
			return false;
		}

		PropertyNotUpdatableEntity that = (PropertyNotUpdatableEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}
		if ( constantData1 != null ? !constantData1.equals( that.constantData1 ) : that.constantData1 != null ) {
			return false;
		}
		if ( constantData2 != null ? !constantData2.equals( that.constantData2 ) : that.constantData2 != null ) {
			return false;
		}

		return true;
	}
