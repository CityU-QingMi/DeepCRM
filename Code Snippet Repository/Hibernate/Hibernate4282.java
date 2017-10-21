		@SuppressWarnings("")
		private Method resolveMethod() {
			try {
				final Method method = declaringClass.getDeclaredMethod( methodName, argumentType );
				method.setAccessible( true );
				return method;
			}
			catch (NoSuchMethodException e) {
				throw new PropertyAccessSerializationException(
						"Unable to resolve setter method on deserialization : " + declaringClass.getName() + "#"
								+ methodName + "(" + argumentType.getName() + ")"
				);
			}
		}
