	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FilePosition that = (FilePosition) o;
		return (this.line == that.line) && Objects.equals(this.column, that.column);
	}
