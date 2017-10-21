	public static Object invokeGetters(Object root, String... names) {
		Preconditions.notNull(root, "Root object must not be null");
		Preconditions.notEmpty(names, "names array must not be null or empty");
		Preconditions.containsNoNullElements(names, "individual names must not be null");
		Object object = root;
		for (String name : names) {
			try {
				if (object == null) {
					throw new JUnitException(String.format("Can not invoke method [%s] on null", name));
				}
				object = object.getClass().getMethod(name).invoke(object);
			}
			catch (ReflectiveOperationException e) {
				throw new JUnitException(
					String.format("Failed to find or invoke method named [%s] in class [%s] for [%s] with root = [%s]",
						name, object.getClass(), object, root),
					e);
			}
		}
		return object;
	}
