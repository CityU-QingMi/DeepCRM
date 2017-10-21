		@Override
		public boolean isValid(List<String> list, ConstraintValidatorContext context) {
			context.disableDefaultConstraintViolation();
			boolean valid = true;
			for (int i = 0; i < list.size(); i++) {
				if ("X".equals(list.get(i))) {
					context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addBeanNode().inIterable().atIndex(i).addConstraintViolation();
					valid = false;
				}
			}
			return valid;
		}
