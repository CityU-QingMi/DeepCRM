	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof LobTestEntity) ) {
			return false;
		}

		LobTestEntity that = (LobTestEntity) o;

		if ( !Arrays.equals( byteLob, that.byteLob ) ) {
			return false;
		}
		if ( !Arrays.equals( charLob, that.charLob ) ) {
			return false;
		}
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( stringLob != null ? !stringLob.equals( that.stringLob ) : that.stringLob != null ) {
			return false;
		}
		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}

		return true;
	}
