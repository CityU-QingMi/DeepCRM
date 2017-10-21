	@Override
	public Object get(Object owner) {
		try {
			// This is needed because until JDK 9 the Reflection API
			// does not use the same caching as used for auto-boxing.
			// See https://bugs.openjdk.java.net/browse/JDK-5043030 for details.
			// The code below can be removed when we move to JDK 9.
			// double and float are intentionally not handled here because
			// the JLS § 5.1.7 does not define caching for boxed values of
			// this types.
			Class<?> type = field.getType();
			if ( type.isPrimitive() ) {
				if ( type == Boolean.TYPE ) {
					return Boolean.valueOf( field.getBoolean( owner ) );
				}
				else if ( type == Byte.TYPE ) {
					return Byte.valueOf( field.getByte( owner ) );
				}
				else if ( type == Character.TYPE ) {
					return Character.valueOf( field.getChar( owner ) );
				}
				else if ( type == Integer.TYPE ) {
					return Integer.valueOf( field.getInt( owner ) );
				}
				else if ( type == Long.TYPE ) {
					return Long.valueOf( field.getLong( owner ) );
				}
				else if ( type == Short.TYPE ) {
					return Short.valueOf( field.getShort( owner ) );
				}
			}
			return field.get( owner );
		}
		catch (Exception e) {
			throw new PropertyAccessException(
					String.format(
							Locale.ROOT,
							"Error accessing field [%s] by reflection for persistent property [%s#%s] : %s",
							field.toGenericString(),
							containerClass.getName(),
							propertyName,
							owner
					),
					e
			);
		}
	}
