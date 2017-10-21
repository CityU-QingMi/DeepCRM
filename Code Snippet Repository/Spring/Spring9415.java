	@Override
	public boolean equals(@Nullable Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		DefaultRequestPath that = (DefaultRequestPath) other;
		return (this.fullPath.equals(that.fullPath) &&
				this.contextPath.equals(that.contextPath) &&
				this.pathWithinApplication.equals(that.pathWithinApplication));
	}
