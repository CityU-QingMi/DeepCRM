	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Middle)) return false;

		final Middle cidMaster = (Middle) o;

		if (id != null ? !id.equals(cidMaster.id) : cidMaster.id != null) return false;

		return true;
	}
