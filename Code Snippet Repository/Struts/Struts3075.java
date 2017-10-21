	private static String XmlEscape(String s) {
		if (s == null)
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '<') {
				sb.append("&lt;");
			} else if (c == '>') {
				sb.append("&gt;");
			} else if (c == '\'') {
				sb.append("&#039;"); // &apos;
			} else if (c == '&') {
				sb.append("&amp;");
			} else if (c == '"') {
				sb.append("&#034;"); // &quot;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
