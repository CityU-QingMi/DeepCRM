		@SuppressWarnings("")
		private Method resolveMethod() {
			try {
				final Method method = declaringClass.getDeclaredMethod( methodName );
				method.setAccessible( true );
				return method;
			}
			catch (NoSuchMethodException e) {
				throw new PropertyAccessSerializationException(
						"Unable to resolve getter method on deserialization : " + declaringClass.getName() + "#" + methodName
				);
			}
		}
