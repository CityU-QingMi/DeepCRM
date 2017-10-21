	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Inner)) return false;

		final Inner cidSuper = (Inner) o;

		if (id != null ? !id.equals(cidSuper.id) : cidSuper.id != null) return false;

		return true;
	}
