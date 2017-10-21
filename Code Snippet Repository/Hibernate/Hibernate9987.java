	@Override
	public Object mapToIdFromMap(Map data) {
		if ( data == null ) {
			return null;
		}

		final Object ret;
		try {
			ret = ReflectHelper.getDefaultConstructor( compositeIdClass ).newInstance();
		}
		catch (Exception e) {
			throw new AuditException( e );
		}

		for ( SingleIdMapper mapper : ids.values() ) {
			if ( !mapper.mapToEntityFromMap( ret, data ) ) {
				return null;
			}
		}

		return ret;
	}
