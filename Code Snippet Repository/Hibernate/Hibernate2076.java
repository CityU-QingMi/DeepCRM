	private Method locateCorrespondingColumnIndexMethod(Method columnNameMethod) throws NoSuchMethodException {
		final Class[] actualParameterTypes = new Class[columnNameMethod.getParameterCount()];
		actualParameterTypes[0] = int.class;
		System.arraycopy(
				columnNameMethod.getParameterTypes(),
				1,
				actualParameterTypes,
				1,
				columnNameMethod.getParameterCount() - 1
		);
		return columnNameMethod.getDeclaringClass().getMethod( columnNameMethod.getName(), actualParameterTypes );
	}
