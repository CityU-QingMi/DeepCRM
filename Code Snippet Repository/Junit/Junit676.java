	public static boolean isValid(String name) {
		if (name == null) {
			return false;
		}
		name = name.trim();

		return !name.isEmpty() && //
				StringUtils.doesNotContainWhitespace(name) && //
				StringUtils.doesNotContainIsoControlCharacter(name) && //
				doesNotContainReservedCharacter(name);
	}
