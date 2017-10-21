		@SuppressWarnings("")
		private static <T> MutabilityPlan<T> createMutabilityPlan(final Class<T> type) {
			if ( type.isAnnotationPresent( Immutable.class ) ) {
				return ImmutableMutabilityPlan.INSTANCE;
			}
			// MutableMutabilityPlan is the "safest" option, but we do not necessarily know how to deepCopy etc...
			return new MutableMutabilityPlan<T>() {
				@Override
				protected T deepCopyNotNull(T value) {
					throw new HibernateException(
							"Not known how to deep copy value of type: [" + type
									.getName() + "]"
					);
				}
			};
		}
