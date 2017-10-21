	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof InnerKey)) return false;

		final InnerKey cidSuperID = (InnerKey) o;

		if (akey != null ? !akey.equals(cidSuperID.akey) : cidSuperID.akey != null) return false;
		if (bkey != null ? !bkey.equals(cidSuperID.bkey) : cidSuperID.bkey != null) return false;

		return true;
	}
