	@Override
	@SuppressWarnings("")
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof InvertibleComparator)) {
			return false;
		}
		InvertibleComparator<T> other = (InvertibleComparator<T>) obj;
		return (this.comparator.equals(other.comparator) && this.ascending == other.ascending);
	}
