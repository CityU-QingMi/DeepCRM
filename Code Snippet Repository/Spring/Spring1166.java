		@Override
		@SuppressWarnings("")
		public Object convertIfNecessary(Object value, @Nullable Class requiredType) {
			if (value instanceof String && Float.class.isAssignableFrom(requiredType)) {
				try {
					return new Float(this.numberFormat.parse((String) value).floatValue());
				}
				catch (ParseException ex) {
					throw new TypeMismatchException(value, requiredType, ex);
				}
			}
			else if (value instanceof String && int.class.isAssignableFrom(requiredType)) {
				return new Integer(5);
			}
			else {
				return value;
			}
		}
