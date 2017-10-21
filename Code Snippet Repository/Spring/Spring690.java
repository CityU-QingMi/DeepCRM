	protected List<Object> resolveConstructorArguments(Object[] args, int start, int end) {
		Object[] constructorArgs = Arrays.copyOfRange(args, start, end);
		for (int i = 0; i < constructorArgs.length; i++) {
			if (constructorArgs[i] instanceof GString) {
				constructorArgs[i] = constructorArgs[i].toString();
			}
			else if (constructorArgs[i] instanceof List) {
				constructorArgs[i] = manageListIfNecessary((List) constructorArgs[i]);
			}
			else if (constructorArgs[i] instanceof Map){
				constructorArgs[i] = manageMapIfNecessary((Map) constructorArgs[i]);
			}
		}
		return Arrays.asList(constructorArgs);
	}
