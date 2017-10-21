	@SuppressWarnings("")
	public static <T> ValueGenerator<T> get(final Class<T> type) {
		final ValueGenerator<?> valueGeneratorSupplier = generators.get(
				type );
		if ( Objects.isNull( valueGeneratorSupplier ) ) {
			throw new HibernateException(
					"Unsupported property type [" + type.getName() + "] for @CreationTimestamp or @UpdateTimestamp generator annotation" );
		}

		return (ValueGenerator<T>) valueGeneratorSupplier;
	}
