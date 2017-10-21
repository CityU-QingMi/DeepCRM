	public static FlushMode getFlushMode(Object value, FlushMode defaultFlushMode) {
		final FlushMode flushMode;
		if (value instanceof FlushMode) {
			flushMode = (FlushMode) value;
		}
		else if (value instanceof javax.persistence.FlushModeType) {
			flushMode = ConfigurationHelper.getFlushMode( (javax.persistence.FlushModeType) value);
		}
		else if (value instanceof String) {
			flushMode = ConfigurationHelper.getFlushMode( (String) value);
		}
		else {
			flushMode = defaultFlushMode;
		}

		if (flushMode == null) {
			throw new PersistenceException("Unable to parse org.hibernate.flushMode: " + value);
		}

		return flushMode;
	}
