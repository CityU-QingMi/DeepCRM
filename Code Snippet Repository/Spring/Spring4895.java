	@Override
	@SuppressWarnings("")
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CompoundComparator)) {
			return false;
		}
		CompoundComparator<T> other = (CompoundComparator<T>) obj;
		return this.comparators.equals(other.comparators);
	}
