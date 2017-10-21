	@Override
	@Nullable
	public Object resolveArgument(MethodParameter parameter, Message<?> message) throws Exception {

		Class<?> paramType = parameter.getParameterType();

		if (Map.class.isAssignableFrom(paramType)) {
			return message.getHeaders();
		}
		else if (MessageHeaderAccessor.class == paramType) {
			MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class);
			return (accessor != null ? accessor : new MessageHeaderAccessor(message));
		}
		else if (MessageHeaderAccessor.class.isAssignableFrom(paramType)) {
			MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class);
			if (accessor != null && paramType.isAssignableFrom(accessor.getClass())) {
				return accessor;
			}
			else {
				Method method = ReflectionUtils.findMethod(paramType, "wrap", Message.class);
				Assert.notNull(method, "Cannot create accessor of type " + paramType + " for message " +  message);
				return ReflectionUtils.invokeMethod(method, null, message);
			}
		}
		else {
			throw new IllegalStateException(
					"Unexpected method parameter type " + paramType + "in method " + parameter.getMethod() + ". "
					+ "@Headers method arguments must be assignable to java.util.Map.");
		}
	}
