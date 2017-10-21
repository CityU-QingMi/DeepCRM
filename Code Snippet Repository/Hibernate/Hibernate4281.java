	@Override
	public void set(Object target, Object value, SessionFactoryImplementor factory) {
		try {
			field.set( target, value );
		}
		catch (Exception e) {
			if (value == null && field.getType().isPrimitive()) {
				throw new PropertyAccessException(
						e,
						String.format(
								Locale.ROOT,
								"Null value was assigned to a property [%s.%s] of primitive type",
								containerClass,
								propertyName
						),
						true,
						containerClass,
						propertyName
				);
			}
			else {
				throw new PropertyAccessException(
						e,
						String.format(
								Locale.ROOT,
								"Could not set field value [%s] value by reflection : [%s.%s]",
								value,
								containerClass,
								propertyName
						),
						true,
						containerClass,
						propertyName
				);
			}
		}
	}
