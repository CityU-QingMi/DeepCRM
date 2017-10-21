	private Iterable<Object[]> allParameters(List<FrameworkMethod> parametersMethods) throws Throwable {
		ArrayList<Iterable<Object[]>> returnedParameters = new ArrayList<Iterable<Object[]>>();
		ArrayList<Object[]> allParameters = new ArrayList<Object[]>();
		Object cachedInstance = null;
		for (FrameworkMethod method : parametersMethods) {
			Object parameters;
			if (method.isStatic()) {
				parameters = method.invokeExplosively(null);
			}
			else {
				if (cachedInstance == null) {
					cachedInstance = getTestClass().getOnlyConstructor().newInstance();
				}
				parameters = method.invokeExplosively(cachedInstance);
			}
			if (parameters instanceof Iterable) {
				returnedParameters.add((Iterable<Object[]>) parameters);
			}
			else {
				throw parametersMethodReturnedWrongType(method);
			}
		}
		for (Iterable<Object[]> parameters : returnedParameters) {
			if (allParameters.isEmpty()) {
				for (Object[] array : parameters) {
					allParameters.add(array);
				}
			}
			else {
				ArrayList<Object[]> newAllParameters = new ArrayList<Object[]>();
				for (Object[] prev : allParameters) {
					for (Object[] array : parameters) {
						Object[] next = Arrays.copyOf(prev, prev.length + array.length);
						System.arraycopy(array, 0, next, prev.length, array.length);
						newAllParameters.add(next);
					}
				}
				allParameters = newAllParameters;
			}
		}
		return allParameters;
	}
