		@Override
		public boolean isValid(ValidPerson value, ConstraintValidatorContext context) {
			if (value.expectsAutowiredValidator) {
				assertNotNull(this.environment);
			}
			boolean valid = (value.name == null || !value.address.street.contains(value.name));
			if (!valid && "Phil".equals(value.name)) {
				context.buildConstraintViolationWithTemplate(
						context.getDefaultConstraintMessageTemplate()).addPropertyNode("address").addConstraintViolation().disableDefaultConstraintViolation();
			}
			return valid;
		}
