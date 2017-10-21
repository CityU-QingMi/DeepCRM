	@Override
	@SuppressWarnings("")
	public <T> T unwrap(Class<T> cls) {
		if ( cls.isInstance( this ) ) {
			return (T) this;
		}
		else if ( cls.isInstance( outputs ) ) {
			return (T) outputs();
		}

		return super.unwrap( cls );
	}
