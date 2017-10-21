	private static Integer extractIntegerValue(Field field) {
		Integer rtn = null;
		try {
			Object value = field.get( null );
			if ( value instanceof Integer ) {
				rtn = (Integer) value;
			}
			else if ( value instanceof Short ) {
				rtn = ( (Short) value ).intValue();
			}
			else if ( value instanceof Long ) {
				if ( (Long) value <= Integer.MAX_VALUE ) {
					rtn = ( (Long) value ).intValue();
				}
			}
		}
		catch (IllegalAccessException ignore) {
		}
		return rtn;
	}
