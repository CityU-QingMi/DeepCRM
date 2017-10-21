	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MiddleKey)) return false;

		final MiddleKey cidMasterID = (MiddleKey) o;

		if (one != null ? !one.equals(cidMasterID.one) : cidMasterID.one != null) return false;
		if (sup != null ? !sup.equals(cidMasterID.sup) : cidMasterID.sup != null) return false;
		if (two != null ? !two.equals(cidMasterID.two) : cidMasterID.two != null) return false;

		return true;
	}
