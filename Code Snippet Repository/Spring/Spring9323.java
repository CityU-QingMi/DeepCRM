	@Override
	public boolean canDecode(ResolvableType elementType, @Nullable MimeType mimeType) {
		if (super.canDecode(elementType, mimeType)) {
			Class<?> outputClass = elementType.getRawClass();
			return (outputClass != null && (outputClass.isAnnotationPresent(XmlRootElement.class) ||
					outputClass.isAnnotationPresent(XmlType.class)));
		}
		else {
			return false;
		}
	}
