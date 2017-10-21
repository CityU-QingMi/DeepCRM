	@Override
	public boolean equals(final Object pObject) {
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof PersonPair)) {
			return false;
		}
		final PersonPair other = (PersonPair) pObject;
		if (getRight() == null) {
			if (other.getRight() != null) {
				return false;
			}
		} else if (!getRight().equals(other.getRight())) {
			return false;
		}
		if (getLeft() == null) {
			if (other.getLeft() != null) {
				return false;
			}
		} else if (!getLeft().equals(other.getLeft())) {
			return false;
		}
		return true;
	}
