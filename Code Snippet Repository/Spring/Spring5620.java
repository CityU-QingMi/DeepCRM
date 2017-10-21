	public static String createSignatureDescriptor(Method method) {
		Class<?>[] params = method.getParameterTypes();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Class<?> param : params) {
			sb.append(toJvmDescriptor(param));
		}
		sb.append(")");
		sb.append(toJvmDescriptor(method.getReturnType()));
		return sb.toString();
	}
