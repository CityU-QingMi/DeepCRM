	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (sourceType.isAssignableTo(STREAM_TYPE)) {
			return matchesFromStream(sourceType.getElementTypeDescriptor(), targetType);
		}
		if (targetType.isAssignableTo(STREAM_TYPE)) {
			return matchesToStream(targetType.getElementTypeDescriptor(), sourceType);
		}
		return false;
	}
