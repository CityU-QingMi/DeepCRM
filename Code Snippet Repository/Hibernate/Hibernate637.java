	void addConverter(AttributeConverterDescriptor descriptor) {
		if ( attributeConverterDescriptorsByClass == null ) {
			attributeConverterDescriptorsByClass = new ConcurrentHashMap<Class, AttributeConverterDescriptor>();
		}

		final Object old = attributeConverterDescriptorsByClass.put(
				descriptor.getAttributeConverter().getClass(),
				descriptor
		);

		if ( old != null ) {
			throw new AssertionFailure(
					String.format(
							Locale.ENGLISH,
							"AttributeConverter class [%s] registered multiple times",
							descriptor.getAttributeConverter().getClass()
					)
			);
		}
	}
