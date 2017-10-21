	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ClasspathResourceSource that = (ClasspathResourceSource) o;
		return Objects.equals(this.classpathResourceName, that.classpathResourceName)
				&& Objects.equals(this.filePosition, that.filePosition);
	}
