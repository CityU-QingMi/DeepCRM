	@Nullable
	private String maybeExtractVariableName(@Nullable String candidateToken) {
		if (!StringUtils.hasLength(candidateToken)) {
			return null;
		}
		if (Character.isJavaIdentifierStart(candidateToken.charAt(0)) &&
				Character.isLowerCase(candidateToken.charAt(0))) {
			char[] tokenChars = candidateToken.toCharArray();
			for (char tokenChar : tokenChars) {
				if (!Character.isJavaIdentifierPart(tokenChar)) {
					return null;
				}
			}
			return candidateToken;
		}
		else {
			return null;
		}
	}
