	private Object callStaticMethodWithDebugInfo(Map context, Class aClass, String methodName,
			Object[] objects) throws MethodFailedException {
		try {
			return super.callStaticMethod(context, aClass, methodName, objects);
		}
		catch(MethodFailedException e) {
			if (LOG.isDebugEnabled()) {
				if (!(e.getReason() instanceof NoSuchMethodException)) {
					// the method exists on the target class, but something went wrong
					LOG.debug("Error calling method through OGNL, class: [{}] method: [{}] args: [{}]", e.getReason(), aClass.getName(), methodName, Arrays.toString(objects));
				}
			}
			throw e;
		}
	}
