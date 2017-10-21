	private Method getGetterFromSetter(Method setter) {
		String getterName = setter.getName().replaceFirst("set", "get");
		try {
			return setter.getDeclaringClass().getMethod(getterName);
		}
		catch (NoSuchMethodException ex) {
			// must be write only
			return null;
		}
	}
