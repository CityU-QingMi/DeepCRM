	private static Constructor resolveConstructor(Class targetClass, List<Type> types) {
		for ( Constructor constructor : targetClass.getConstructors() ) {
			final Class[] argumentTypes = constructor.getParameterTypes();
			if ( argumentTypes.length != types.size() ) {
				continue;
			}

			boolean allMatched = true;
			for ( int i = 0; i < argumentTypes.length; i++ ) {
				if ( ! areAssignmentCompatible( argumentTypes[i], types.get( i ).getReturnedClass() ) ) {
					allMatched = false;
					break;
				}
			}
			if ( !allMatched ) {
				continue;
			}

			return constructor;
		}

		throw new IllegalArgumentException( "Could not locate appropriate constructor on class : " + targetClass.getName() );
	}
