	@Override
	@Nullable
	public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (sourceType.isAssignableTo(STREAM_TYPE)) {
			return convertFromStream((Stream<?>) source, sourceType, targetType);
		}
		if (targetType.isAssignableTo(STREAM_TYPE)) {
			return convertToStream(source, sourceType, targetType);
		}
		// Should not happen
		throw new IllegalStateException("Unexpected source/target types");
	}
