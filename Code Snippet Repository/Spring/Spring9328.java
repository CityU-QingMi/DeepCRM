	@Override
	public boolean canEncode(ResolvableType elementType, @Nullable MimeType mimeType) {
		if (super.canEncode(elementType, mimeType)) {
			Class<?> outputClass = elementType.resolve(Object.class);
			return (outputClass.isAnnotationPresent(XmlRootElement.class) ||
					outputClass.isAnnotationPresent(XmlType.class));
		}
		else {
			return false;
		}

	}
