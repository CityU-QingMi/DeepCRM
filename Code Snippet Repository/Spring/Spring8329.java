	public ResultMatcher methodCall(final Object obj) {
		return result -> {
			if (!(obj instanceof MethodInvocationInfo)) {
				fail(String.format("The supplied object [%s] is not an instance of %s. " +
						"Ensure that you invoke the handler method via MvcUriComponentsBuilder.on().",
						obj, MethodInvocationInfo.class.getName()));
			}
			MethodInvocationInfo invocationInfo = (MethodInvocationInfo) obj;
			Method expected = invocationInfo.getControllerMethod();
			Method actual = getHandlerMethod(result).getMethod();
			assertEquals("Handler method", expected, actual);
		};
	}
