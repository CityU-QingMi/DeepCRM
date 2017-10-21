	private static int defaultMethodSorter(Method method1, Method method2) {
		String name1 = method1.getName();
		String name2 = method2.getName();
		int comparison = Integer.compare(name1.hashCode(), name2.hashCode());
		if (comparison == 0) {
			comparison = name1.compareTo(name2);
			if (comparison == 0) {
				comparison = method1.toString().compareTo(method2.toString());
			}
		}
		return comparison;
	}
