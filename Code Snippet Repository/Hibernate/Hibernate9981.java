	private boolean isPrimitive(Setter setter, PropertyData propertyData, Class<?> cls) {
		if ( cls == null ) {
			throw new HibernateException( "No field found for property: " + propertyData.getName() );
		}

		if ( setter instanceof SetterFieldImpl ) {
			// In a direct setter, getMethod() returns null
			// Trying to look up the field
			try {
				return cls.getDeclaredField( propertyData.getBeanName() ).getType().isPrimitive();
			}
			catch (NoSuchFieldException e) {
				return isPrimitive( setter, propertyData, cls.getSuperclass() );
			}
		}
		else {
			return setter.getMethod().getParameterTypes()[0].isPrimitive();
		}
	}
