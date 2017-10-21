	private boolean isValidQualifiedId(@Nullable Token node) {
		if (node == null || node.kind == TokenKind.LITERAL_STRING) {
			return false;
		}
		if (node.kind == TokenKind.DOT || node.kind == TokenKind.IDENTIFIER) {
			return true;
		}
		String value = node.stringValue();
		return (StringUtils.hasLength(value) && VALID_QUALIFIED_ID_PATTERN.matcher(value).matches());
	}
