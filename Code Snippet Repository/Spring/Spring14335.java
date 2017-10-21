	@Override
	public Object convertValue(Object value, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (targetType.getType() == Void.class || targetType.getType() == Void.TYPE) {
			return null;
		}
		if (conversionService.canConvert(sourceType, targetType)) {
			return conversionService.convert(value, sourceType, targetType);
		}
		if (!String.class.isAssignableFrom(sourceType.getType())) {
			PropertyEditor editor = delegate.findCustomEditor(sourceType.getType(), null);
			editor.setValue(value);
			return editor.getAsText();
		}
		return delegate.convertIfNecessary(value, targetType.getType());
	}
