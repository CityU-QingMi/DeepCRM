	@Nullable
	protected Object invokeListenerMethod(String methodName, Object[] arguments) throws JMSException {
		try {
			MethodInvoker methodInvoker = new MethodInvoker();
			methodInvoker.setTargetObject(getDelegate());
			methodInvoker.setTargetMethod(methodName);
			methodInvoker.setArguments(arguments);
			methodInvoker.prepare();
			return methodInvoker.invoke();
		}
		catch (InvocationTargetException ex) {
			Throwable targetEx = ex.getTargetException();
			if (targetEx instanceof JMSException) {
				throw (JMSException) targetEx;
			}
			else {
				throw new ListenerExecutionFailedException(
						"Listener method '" + methodName + "' threw exception", targetEx);
			}
		}
		catch (Throwable ex) {
			throw new ListenerExecutionFailedException("Failed to invoke target method '" + methodName +
					"' with arguments " + ObjectUtils.nullSafeToString(arguments), ex);
		}
	}
