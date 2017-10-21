	protected void sortMethods(List<FrameworkMethod> computedTestMethods) {
		if ( CollectionHelper.isEmpty( computedTestMethods ) ) {
			return;
		}
		Collections.sort(
				computedTestMethods, new Comparator<FrameworkMethod>() {
					@Override
					public int compare(FrameworkMethod o1, FrameworkMethod o2) {
						return o1.getName().compareTo( o2.getName() );
					}
				}
		);
	}
