	public static Method findGetterMethod(Class containerClass, String propertyName) {
		Class checkClass = containerClass;
		Method getter = null;

		// check containerClass, and then its super types (if any)
		while ( getter == null && checkClass != null ) {
			if ( checkClass.equals( Object.class ) ) {
				break;
			}

			getter = getGetterOrNull( checkClass, propertyName );
			checkClass = checkClass.getSuperclass();
		}

		// if no getter found yet, check all implemented interfaces
		if ( getter == null ) {
			getter = getGetterOrNull( containerClass.getInterfaces(), propertyName );
		}

		if ( getter == null ) {
			throw new PropertyNotFoundException(
					String.format(
							Locale.ROOT,
							"Could not locate getter method for property [%s#%s]",
							containerClass.getName(),
							propertyName
					)
			);
		}

		getter.setAccessible( true );
		return getter;
	}
