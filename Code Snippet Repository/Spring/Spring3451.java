	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		IdentifiableApplicationEvent that = (IdentifiableApplicationEvent) o;

		return this.id.equals(that.id);

	}
