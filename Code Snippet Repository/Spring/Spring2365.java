	@Nullable
	private FormatStyle convertStyleCharacter(char c) {
		switch (c) {
			case 'S': return FormatStyle.SHORT;
			case 'M': return FormatStyle.MEDIUM;
			case 'L': return FormatStyle.LONG;
			case 'F': return FormatStyle.FULL;
			case '-': return null;
			default: throw new IllegalArgumentException("Invalid style character '" + c + "'");
		}
	}
