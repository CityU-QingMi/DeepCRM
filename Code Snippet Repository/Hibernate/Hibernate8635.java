	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OuterKey)) return false;

		final OuterKey cidDetailID = (OuterKey) o;

		if (detailId != null ? !detailId.equals(cidDetailID.detailId) : cidDetailID.detailId != null) return false;
		if (master != null ? !master.equals(cidDetailID.master) : cidDetailID.master != null) return false;

		return true;
	}
