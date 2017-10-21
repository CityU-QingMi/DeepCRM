		public <X> X get(int i, Class<X> type) {
			final Object result = get( i );
			if ( result != null && !type.isInstance( result ) ) {
				throw new IllegalArgumentException(
						String.format(
								"Requested tuple value [index=%s, realType=%s] cannot be assigned to requested type [%s]",
								i,
								result.getClass().getName(),
								type.getName()
						)
				);
			}
			return (X) result;
		}
