	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		UniqueId that = (UniqueId) o;
		return this.segments.equals(that.segments);
	}
