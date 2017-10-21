	private static Method getMethod(Class<?> controllerType, final String methodName, final Object... args) {
		MethodFilter selector = method -> {
			String name = method.getName();
			int argLength = method.getParameterCount();
			return (name.equals(methodName) && argLength == args.length);
		};
		Set<Method> methods = MethodIntrospector.selectMethods(controllerType, selector);
		if (methods.size() == 1) {
			return methods.iterator().next();
		}
		else if (methods.size() > 1) {
			throw new IllegalArgumentException(String.format(
					"Found two methods named '%s' accepting arguments %s in controller %s: [%s]",
					methodName, Arrays.asList(args), controllerType.getName(), methods));
		}
		else {
			throw new IllegalArgumentException("No method named '" + methodName + "' with " + args.length +
					" arguments found in controller " + controllerType.getName());
		}
	}
