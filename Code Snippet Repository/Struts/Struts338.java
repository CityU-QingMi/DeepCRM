    private Object callMethodWithDebugInfo(Map context, Object object, String methodName, Object[] objects) throws MethodFailedException {
        try {
            return super.callMethod(context, object, methodName, objects);
		}
		catch(MethodFailedException e) {
			if (LOG.isDebugEnabled()) {
				if (!(e.getReason() instanceof NoSuchMethodException)) {
					// the method exists on the target object, but something went wrong
                    LOG.debug("Error calling method through OGNL: object: [{}] method: [{}] args: [{}]", e.getReason(), object.toString(), methodName, Arrays.toString(objects));
                }
            }
			throw e;
		}
	}
