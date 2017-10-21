	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ClassSource that = (ClassSource) o;
		return Objects.equals(this.javaClass, that.javaClass) && Objects.equals(this.filePosition, that.filePosition);
	}
