	@Override
	@SuppressWarnings({ "", "" })
	public void accept(EnumSource enumSource) {
		Class enumClass = enumSource.value();
		this.constants = EnumSet.allOf(enumClass);

		EnumSource.Mode mode = enumSource.mode();
		String[] declaredConstantNames = enumSource.names();
		if (declaredConstantNames.length > 0) {
			Set<String> uniqueNames = stream(declaredConstantNames).collect(toSet());
			Preconditions.condition(uniqueNames.size() == declaredConstantNames.length,
				() -> "Duplicate enum constant name(s) found in " + enumSource);
			mode.validate(enumSource, uniqueNames);
			this.constants.removeIf(constant -> !mode.select(constant, uniqueNames));
		}
	}
