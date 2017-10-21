	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SimpSubscription)) {
			return false;
		}
		SimpSubscription otherSubscription = (SimpSubscription) other;
		return (ObjectUtils.nullSafeEquals(getSession(), otherSubscription.getSession()) &&
				this.id.equals(otherSubscription.getId()));
	}
