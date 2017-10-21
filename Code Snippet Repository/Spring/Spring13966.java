	private String escapeSockJsSpecialChars(char[] characters) {
		StringBuilder result = new StringBuilder();
		for (char c : characters) {
			if (isSockJsSpecialChar(c)) {
				result.append('\\').append('u');
				String hex = Integer.toHexString(c).toLowerCase();
				for (int i = 0; i < (4 - hex.length()); i++) {
					result.append('0');
				}
				result.append(hex);
			}
			else {
				result.append(c);
			}
		}
		return result.toString();
	}
