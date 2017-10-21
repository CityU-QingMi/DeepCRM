	public static boolean isPrimitiveOrUnboxableSupportedNumber(@Nullable String descriptor) {
		if (descriptor == null) {
			return false;
		}
		if (descriptor.length() == 1) {
			return "DFIJ".contains(descriptor);
		}
		if (descriptor.startsWith("Ljava/lang/")) {
			String name = descriptor.substring("Ljava/lang/".length());
			if (name.equals("Double") || name.equals("Float") || name.equals("Integer") || name.equals("Long")) {
				return true;
			}
		}
		return false;
	}
