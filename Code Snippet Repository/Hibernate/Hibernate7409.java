		@Override
		public boolean onFlushDirty(Object entity,
				Serializable id,
				Object[] currentState,
				Object[] previousState,
				String[] propertyNames,
				Type[] types) {
			calls.incrementAndGet();
			return false;
		}
