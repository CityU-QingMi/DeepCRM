	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof EndInvalidationCommand)) {
			return false;
		}

		EndInvalidationCommand that = (EndInvalidationCommand) o;

		if (cacheName == null ? cacheName != null : !cacheName.equals(that.cacheName)) {
			return false;
		}
		if (!Arrays.equals(keys, that.keys)) {
			return false;
		}
		return !(lockOwner != null ? !lockOwner.equals(that.lockOwner) : that.lockOwner != null);

	}
