	private void performCallbackInvocation(Method callback, Object target) {
		try {
			callback.invoke( target, NO_ARGS );
		}
		catch (InvocationTargetException e) {
			throw new CallbackException( callback, e.getTargetException() );
		}
		catch (IllegalAccessException e) {
			throw new CallbackException( callback, e );
		}
	}
