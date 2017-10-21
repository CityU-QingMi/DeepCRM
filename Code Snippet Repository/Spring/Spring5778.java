	public static Object[] setupArgumentsForVarargsInvocation(Class<?>[] requiredParameterTypes, Object... args) {
		// Check if array already built for final argument
		int parameterCount = requiredParameterTypes.length;
		int argumentCount = args.length;

		// Check if repackaging is needed...
		if (parameterCount != args.length ||
				requiredParameterTypes[parameterCount - 1] !=
						(args[argumentCount - 1] != null ? args[argumentCount - 1].getClass() : null)) {

			int arraySize = 0;  // zero size array if nothing to pass as the varargs parameter
			if (argumentCount >= parameterCount) {
				arraySize = argumentCount - (parameterCount - 1);
			}

			// Create an array for the varargs arguments
			Object[] newArgs = new Object[parameterCount];
			System.arraycopy(args, 0, newArgs, 0, newArgs.length - 1);

			// Now sort out the final argument, which is the varargs one. Before entering this method,
			// the arguments should have been converted to the box form of the required type.
			Class<?> componentType = requiredParameterTypes[parameterCount - 1].getComponentType();
			Object repackagedArgs = Array.newInstance(componentType, arraySize);
			for (int i = 0; i < arraySize; i++) {
				Array.set(repackagedArgs, i, args[parameterCount - 1 + i]);
			}
			newArgs[newArgs.length - 1] = repackagedArgs;
			return newArgs;
		}
		return args;
	}
