	@Override
	public boolean equals(Object o) {
		if (!super.equals(o)) {
			return false;
		}
		if (o instanceof BeginInvalidationCommand) {
			BeginInvalidationCommand bic = (BeginInvalidationCommand) o;
			return Objects.equals(lockOwner, bic.lockOwner);
		}
		else {
			return false;
		}
	}
