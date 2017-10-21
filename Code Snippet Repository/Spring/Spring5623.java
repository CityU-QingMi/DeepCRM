	public static boolean isPrimitiveArray(@Nullable String descriptor) {
		if (descriptor == null) {
			return false;
		}
		boolean primitive = true;
		for (int i = 0, max = descriptor.length(); i < max; i++) {
			char ch = descriptor.charAt(i);
			if (ch == '[') {
				continue;
			}
			primitive = (ch != 'L');
			break;
		}
		return primitive;
	}
