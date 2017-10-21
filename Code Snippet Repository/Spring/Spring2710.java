	@Override
	public void validate(@Nullable Object target, Errors errors, @Nullable Object... validationHints) {
		if (this.targetValidator != null) {
			Set<Class<?>> groups = new LinkedHashSet<>();
			if (validationHints != null) {
				for (Object hint : validationHints) {
					if (hint instanceof Class) {
						groups.add((Class<?>) hint);
					}
				}
			}
			processConstraintViolations(
					this.targetValidator.validate(target, groups.toArray(new Class<?>[groups.size()])), errors);
		}
	}
