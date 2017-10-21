	public static Method findSetterMethod(Class containerClass, String propertyName, Class propertyType) {
		Class checkClass = containerClass;
		Method setter = null;

		// check containerClass, and then its super types (if any)
		while ( setter == null && checkClass != null ) {
			if ( checkClass.equals( Object.class ) ) {
				break;
			}

			setter = setterOrNull( checkClass, propertyName, propertyType );
			checkClass = checkClass.getSuperclass();
		}

		// if no setter found yet, check all implemented interfaces
		if ( setter == null ) {
			setter = setterOrNull( containerClass.getInterfaces(), propertyName, propertyType );
//			for ( Class theInterface : containerClass.getInterfaces() ) {
//				setter = setterOrNull( theInterface, propertyName, propertyType );
//				if ( setter != null ) {
//					break;
//				}
//			}
		}

		if ( setter == null ) {
			throw new PropertyNotFoundException(
					String.format(
							Locale.ROOT,
							"Could not locate setter method for property [%s#%s]",
							containerClass.getName(),
							propertyName
					)
			);
		}

		setter.setAccessible( true );
		return setter;
	}
