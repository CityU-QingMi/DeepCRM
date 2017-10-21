	@Override
	@SuppressWarnings("")
	public int compare(T o1, T o2) {
		Assert.state(!this.comparators.isEmpty(),
				"No sort definitions have been added to this CompoundComparator to compare");
		for (InvertibleComparator comparator : this.comparators) {
			int result = comparator.compare(o1, o2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
