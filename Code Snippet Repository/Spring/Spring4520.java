	@Override
	public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (source == null) {
			return Optional.empty();
		}
		else if (source instanceof Optional) {
			return source;
		}
		else if (targetType.getResolvableType().hasGenerics()) {
			Object target = this.conversionService.convert(source, sourceType, new GenericTypeDescriptor(targetType));
			if (target == null || (target.getClass().isArray() && Array.getLength(target) == 0) ||
						(target instanceof Collection && ((Collection) target).isEmpty())) {
				return Optional.empty();
			}
			return Optional.of(target);
		}
		else {
			return Optional.of(source);
		}
	}
