	private static boolean collectionCompare(Collection<?> boundCollection, Object candidateValue, BindStatus bindStatus) {
		try {
			if (boundCollection.contains(candidateValue)) {
				return true;
			}
		}
		catch (ClassCastException ex) {
			// Probably from a TreeSet - ignore.
		}
		return exhaustiveCollectionCompare(boundCollection, candidateValue, bindStatus);
	}
