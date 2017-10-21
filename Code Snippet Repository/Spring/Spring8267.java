	private static void appendNonMatchingSetsErrorMessage(
			Set<String> assertionSet, Set<String> incorrectSet, StringBuilder sb) {

		Set<String> tempSet = new HashSet<>();
		tempSet.addAll(incorrectSet);
		tempSet.removeAll(assertionSet);

		if (tempSet.size() > 0) {
			sb.append("Set has too many elements:\n");
			for (Object element : tempSet) {
				sb.append('-');
				sb.append(element);
				sb.append('\n');
			}
		}

		tempSet = new HashSet<>();
		tempSet.addAll(assertionSet);
		tempSet.removeAll(incorrectSet);

		if (tempSet.size() > 0) {
			sb.append("Set is missing elements:\n");
			for (Object element : tempSet) {
				sb.append('-');
				sb.append(element);
				sb.append('\n');
			}
		}
	}
