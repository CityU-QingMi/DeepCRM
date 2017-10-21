	private static Date asDate(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof Date) {
			return (Date) o;
		}
		if (o instanceof Number) {
			return new Date(System.currentTimeMillis() +
					NumberUtils.convertNumberToTargetClass((Number) o, Long.class));
		}
		throw new IllegalArgumentException(
				"expected Date or Number, but actual type was: " + o.getClass());
	}
