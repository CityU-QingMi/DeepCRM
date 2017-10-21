	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DirectorySource that = (DirectorySource) o;
		return this.directory.equals(that.directory);
	}
