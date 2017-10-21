	private Validator testValidator(final String invalidValue) {
		return new Validator() {
			@Override
			public boolean supports(Class<?> clazz) {
				return String.class.isAssignableFrom(clazz);
			}
			@Override
			public void validate(@Nullable Object target, Errors errors) {
				String value = (String) target;
				if (invalidValue.equals(value)) {
					errors.reject("not a valid value");
				}
			}
		};
	}
