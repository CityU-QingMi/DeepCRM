	public static <E extends Enum<?>> E caseInsensitiveValueOf(E[] enumValues, String constant) {
		for (E candidate : enumValues) {
			if (candidate.toString().equalsIgnoreCase(constant)) {
				return candidate;
			}
		}
		throw new IllegalArgumentException(
				String.format("constant [%s] does not exist in enum type %s",
						constant, enumValues.getClass().getComponentType().getName()));
	}
