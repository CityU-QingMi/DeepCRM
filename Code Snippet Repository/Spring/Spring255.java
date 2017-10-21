	private static String format(String[] names) {
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (int i = 0; i < names.length; i++) {
			sb.append(names[i]);
			if ((i + 1) < names.length) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
