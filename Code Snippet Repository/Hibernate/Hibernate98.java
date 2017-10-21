		@Override
		public boolean onFlushDirty(
			Object entity,
			Serializable id,
			Object[] currentState,
			Object[] previousState,
			String[] propertyNames,
			Type[] types) {
				LOGGER.debugv( "Entity {0}#{1} changed from {2} to {3}",
					entity.getClass().getSimpleName(),
					id,
					Arrays.toString( previousState ),
					Arrays.toString( currentState )
				);
				return super.onFlushDirty( entity, id, currentState,
					previousState, propertyNames, types
			);
		}
