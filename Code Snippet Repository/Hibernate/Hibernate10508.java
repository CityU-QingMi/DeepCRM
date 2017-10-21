	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof SimpleEntity ) ) {
			return false;
		}

		SimpleEntity that = (SimpleEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( simpleProperty != null ? !simpleProperty.equals( that.simpleProperty ) : that.simpleProperty != null ) {
			return false;
		}

		return true;
	}
