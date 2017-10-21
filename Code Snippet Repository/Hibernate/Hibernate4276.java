		@SuppressWarnings("")
		private Field resolveField() {
			try {
				return declaringClass.getDeclaredField( propertyName );
			}
			catch (NoSuchFieldException e) {
				throw new PropertyAccessSerializationException(
						"Unable to resolve field on deserialization : " + declaringClass.getName() + "#" + propertyName
				);
			}
		}
