	private Object resolveArgument(Class<?> paramType, ServletResponse response) throws IOException {
		if (OutputStream.class.isAssignableFrom(paramType)) {
			return response.getOutputStream();
		}
		else if (Writer.class.isAssignableFrom(paramType)) {
			return response.getWriter();
		}

		// Should never happen...
		throw new UnsupportedOperationException("Unknown parameter type: " + paramType);
	}
