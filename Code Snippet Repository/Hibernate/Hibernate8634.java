	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Outer)) return false;

		final Outer cidDetail = (Outer) o;

		if (id != null ? !id.equals(cidDetail.id) : cidDetail.id != null) return false;

		return true;
	}
