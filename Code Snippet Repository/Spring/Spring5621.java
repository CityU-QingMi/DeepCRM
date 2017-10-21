	public static String createSignatureDescriptor(Constructor<?> ctor) {
		Class<?>[] params = ctor.getParameterTypes();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Class<?> param : params) {
			sb.append(toJvmDescriptor(param));
		}
		sb.append(")V");
		return sb.toString();
	}
