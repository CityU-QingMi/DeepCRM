	protected void lazyLoadArguments() {
		// Shortcut if no args need to be loaded
		if (ObjectUtils.isEmpty(this.arguments)) {
			return;
		}

		// Expose indexed variables as well as parameter names (if discoverable)
		String[] paramNames = this.parameterNameDiscoverer.getParameterNames(this.method);
		int paramCount = (paramNames != null ? paramNames.length : this.method.getParameterCount());
		int argsCount = this.arguments.length;

		for (int i = 0; i < paramCount; i++) {
			Object value = null;
			if (argsCount > paramCount && i == paramCount - 1) {
				// Expose remaining arguments as vararg array for last parameter
				value = Arrays.copyOfRange(this.arguments, i, argsCount);
			}
			else if (argsCount > i) {
				// Actual argument found - otherwise left as null
				value = this.arguments[i];
			}
			setVariable("a" + i, value);
			setVariable("p" + i, value);
			if (paramNames != null) {
				setVariable(paramNames[i], value);
			}
		}
	}
