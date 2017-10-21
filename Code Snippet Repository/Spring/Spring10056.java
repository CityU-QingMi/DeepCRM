	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof Pojo) {
			Pojo other = (Pojo) o;
			return this.foo.equals(other.foo) && this.bar.equals(other.bar);
		}
		return false;
	}
