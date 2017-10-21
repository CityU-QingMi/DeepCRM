		private Class<?> getClassFromGenericArgument(java.lang.reflect.Type type) {
			if ( type instanceof Class ) {
				return (Class) type;
			}
			else if ( type instanceof TypeVariable ) {
				final java.lang.reflect.Type upperBound = ( (TypeVariable) type ).getBounds()[0];
				return getClassFromGenericArgument( upperBound );
			}
			else if ( type instanceof ParameterizedType ) {
				final java.lang.reflect.Type rawType = ( (ParameterizedType) type ).getRawType();
				return getClassFromGenericArgument( rawType );
			}
			else {
				throw new AssertionFailure(
						"Fail to process type argument in a generic declaration. Member : " + getMemberDescription()
								+ " Type: " + type.getClass()
				);
			}
		}
