		@Override
		public <X> X get(String alias, Class<X> type) {
			final Object untyped = get( alias );
			if ( untyped != null ) {
				if ( !type.isInstance( untyped ) ) {
					throw new IllegalArgumentException(
							String.format(
									"Requested tuple value [alias=%s, value=%s] cannot be assigned to requested type [%s]",
									alias,
									untyped,
									type.getName()
							)
					);
				}
			}
			return (X) untyped;
		}
