	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		CompositeTestSource that = (CompositeTestSource) obj;
		return this.sources.equals(that.sources);
	}
