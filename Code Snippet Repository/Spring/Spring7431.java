	@Test
	public void customValidation() throws Exception {
		DefaultMessageHandlerMethodFactory instance = createInstance();
		instance.setValidator(new Validator() {
			@Override
			public boolean supports(Class<?> clazz) {
				return String.class.isAssignableFrom(clazz);
			}
			@Override
			public void validate(Object target, Errors errors) {
				String value = (String) target;
				if ("failure".equals(value)) {
					errors.reject("not a valid value");
				}
			}
		});
		instance.afterPropertiesSet();

		InvocableHandlerMethod invocableHandlerMethod =
				createInvocableHandlerMethod(instance, "payloadValidation", String.class);
		thrown.expect(MethodArgumentNotValidException.class);
		invocableHandlerMethod.invoke(MessageBuilder.withPayload("failure").build());
	}
