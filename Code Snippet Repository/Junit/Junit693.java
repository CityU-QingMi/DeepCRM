	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FileSource that = (FileSource) o;
		return Objects.equals(this.file, that.file) && Objects.equals(this.filePosition, that.filePosition);
	}
