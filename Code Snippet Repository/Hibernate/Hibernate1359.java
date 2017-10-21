	private AnnotationException buildIllegalSortCombination() {
		return new AnnotationException(
				String.format(
						"Illegal combination of annotations on %s.  Only one of @%s, @%s and @%s can be used",
						safeCollectionRole(),
						Sort.class.getName(),
						SortNatural.class.getName(),
						SortComparator.class.getName()
				)
		);
	}
