		@SuppressWarnings("")
		private Method resolveMethod() {
			try {
				return declaringClass.getDeclaredMethod( methodName );
			}
			catch (NoSuchMethodException e) {
				throw new PropertyAccessSerializationException(
						"Unable to resolve getter method on deserialization : " + declaringClass.getName() + "#" + methodName
				);
			}
		}
