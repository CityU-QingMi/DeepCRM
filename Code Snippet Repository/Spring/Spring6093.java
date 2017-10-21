	public static String varargsFunctionReverseStringsAndMerge2(int j, String... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append(j);
		if (strings != null) {
			for (int i = strings.length - 1; i >= 0; i--) {
				sb.append(strings[i]);
			}
		}
		return sb.toString();
	}
