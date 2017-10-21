	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();
		if ("equals".equals(name)) {
			return (proxy == args[0]);
		}
		else if ("hashCode".equals(name)) {
			return hashCode();
		}
		else if ("toString".equals(name)) {
			return toString();
		}
		else if ("transformClass".equals(name)) {
			return transform((String) args[0], (byte[]) args[1], (CodeSource) args[2], (ClassLoader) args[3]);
		}
		else {
			throw new IllegalArgumentException("Unknown method: " + method);
		}
	}
