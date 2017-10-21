	@Override
	@Nullable
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
		else if ("initialize".equals(name)) {
			initialize((Hashtable<?, ?>) args[0]);
			return null;
		}
		else if ("preProcess".equals(name)) {
			return preProcess((String) args[0], (byte[]) args[1]);
		}
		else {
			throw new IllegalArgumentException("Unknown method: " + method);
		}
	}
