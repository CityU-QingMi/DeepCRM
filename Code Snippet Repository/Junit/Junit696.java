	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PackageSource that = (PackageSource) o;
		return Objects.equals(this.packageName, that.packageName);
	}
