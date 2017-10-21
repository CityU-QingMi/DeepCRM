	public PropertySetterAccessException(
			Throwable cause,
			Class persistentClass,
			String propertyName,
			Class expectedType,
			Object target,
			Object value) {
		super(
				cause,
				String.format(
						"IllegalArgumentException occurred while calling setter for property [%s.%s (expected type = %s)]; " +
								"target = [%s], property value = [%s]",
						persistentClass.getName(),
						propertyName,
						expectedType.getName(),
						target,
						value
				),
				true,
				persistentClass,
				propertyName
		);
	}
