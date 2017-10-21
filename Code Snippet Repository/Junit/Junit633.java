	public static String nullSafeToString(Object obj) {
		if (obj == null) {
			return "null";
		}
		else if (obj.getClass().isArray()) {
			if (obj.getClass().getComponentType().isPrimitive()) {
				if (obj instanceof boolean[]) {
					return Arrays.toString((boolean[]) obj);
				}
				if (obj instanceof char[]) {
					return Arrays.toString((char[]) obj);
				}
				if (obj instanceof short[]) {
					return Arrays.toString((short[]) obj);
				}
				if (obj instanceof byte[]) {
					return Arrays.toString((byte[]) obj);
				}
				if (obj instanceof int[]) {
					return Arrays.toString((int[]) obj);
				}
				if (obj instanceof long[]) {
					return Arrays.toString((long[]) obj);
				}
				if (obj instanceof float[]) {
					return Arrays.toString((float[]) obj);
				}
				if (obj instanceof double[]) {
					return Arrays.toString((double[]) obj);
				}
			}
			return Arrays.deepToString((Object[]) obj);
		}
		// else
		return obj.toString();
	}
