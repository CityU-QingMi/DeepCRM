	static boolean convertArguments(TypeConverter converter, Object[] arguments, Executable executable,
			@Nullable Integer varargsPosition) throws EvaluationException {

		boolean conversionOccurred = false;
		if (varargsPosition == null) {
			for (int i = 0; i < arguments.length; i++) {
				TypeDescriptor targetType = new TypeDescriptor(MethodParameter.forExecutable(executable, i));
				Object argument = arguments[i];
				arguments[i] = converter.convertValue(argument, TypeDescriptor.forObject(argument), targetType);
				conversionOccurred |= (argument != arguments[i]);
			}
		}
		else {
			// Convert everything up to the varargs position
			for (int i = 0; i < varargsPosition; i++) {
				TypeDescriptor targetType = new TypeDescriptor(MethodParameter.forExecutable(executable, i));
				Object argument = arguments[i];
				arguments[i] = converter.convertValue(argument, TypeDescriptor.forObject(argument), targetType);
				conversionOccurred |= (argument != arguments[i]);
			}
			MethodParameter methodParam = MethodParameter.forExecutable(executable, varargsPosition);
			if (varargsPosition == arguments.length - 1) {
				// If the target is varargs and there is just one more argument
				// then convert it here
				TypeDescriptor targetType = new TypeDescriptor(methodParam);
				Object argument = arguments[varargsPosition];
				TypeDescriptor sourceType = TypeDescriptor.forObject(argument);
				arguments[varargsPosition] = converter.convertValue(argument, sourceType, targetType);
				// Three outcomes of that previous line:
				// 1) the input argument was already compatible (ie. array of valid type) and nothing was done
				// 2) the input argument was correct type but not in an array so it was made into an array
				// 3) the input argument was the wrong type and got converted and put into an array
				if (argument != arguments[varargsPosition] &&
						!isFirstEntryInArray(argument, arguments[varargsPosition])) {
					conversionOccurred = true; // case 3
				}
			}
			else {
				// Convert remaining arguments to the varargs element type
				TypeDescriptor targetType = new TypeDescriptor(methodParam).getElementTypeDescriptor();
				Assert.state(targetType != null, "No element type");
				for (int i = varargsPosition; i < arguments.length; i++) {
					Object argument = arguments[i];
					arguments[i] = converter.convertValue(argument, TypeDescriptor.forObject(argument), targetType);
					conversionOccurred |= (argument != arguments[i]);
				}
			}
		}
		return conversionOccurred;
	}
