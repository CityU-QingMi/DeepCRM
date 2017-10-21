	public static String toJvmDescriptor(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		if (clazz.isArray()) {
			while (clazz.isArray()) {
				sb.append("[");
				clazz = clazz.getComponentType();
			}
		}
		if (clazz.isPrimitive()) {
			if (clazz == Void.TYPE) {
				sb.append('V');
			}
			else if (clazz == Integer.TYPE) {
				sb.append('I');
			}
			else if (clazz == Boolean.TYPE) {
				sb.append('Z');
			}
			else if (clazz == Character.TYPE) {
				sb.append('C');
			}
			else if (clazz == Long.TYPE) {
				sb.append('J');
			}
			else if (clazz == Double.TYPE) {
				sb.append('D');
			}
			else if (clazz == Float.TYPE) {
				sb.append('F');
			}
			else if (clazz == Byte.TYPE) {
				sb.append('B');
			}
			else if (clazz == Short.TYPE) {
				sb.append('S');
			}
		}
		else {
			sb.append("L");
			sb.append(clazz.getName().replace('.', '/'));
			sb.append(";");
		}
		return sb.toString();
	}
