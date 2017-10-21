	@Override
	protected boolean includeOperation(Method method, String beanKey) {
		PropertyDescriptor pd = BeanUtils.findPropertyForMethod(method);
		if (pd != null) {
			if (hasManagedAttribute(method)) {
				return true;
			}
		}
		return hasManagedOperation(method);
	}
