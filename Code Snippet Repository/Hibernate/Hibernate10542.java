	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof LobSerializableTestEntity) ) {
			return false;
		}

		LobSerializableTestEntity that = (LobSerializableTestEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( obj != null ? !obj.equals( that.obj ) : that.obj != null ) {
			return false;
		}

		return true;
	}
