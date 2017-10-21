	public static boolean containsSqlScriptDelimiters(String script, String delim) {
		boolean inLiteral = false;
		for (int i = 0; i < script.length(); i++) {
			if (script.charAt(i) == '\'') {
				inLiteral = !inLiteral;
			}
			if (!inLiteral && script.startsWith(delim, i)) {
				return true;
			}
		}
		return false;
	}
