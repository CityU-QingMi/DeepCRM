	@Override
	public boolean mapToEntityFromMap(Object obj, Map data) {
		if ( data == null || obj == null ) {
			return false;
		}

		final Getter getter = ReflectionTools.getGetter( obj.getClass(), idPropertyData, getServiceRegistry() );
		final Setter setter = ReflectionTools.getSetter( obj.getClass(), idPropertyData, getServiceRegistry() );

		try {
			final Object subObj = ReflectHelper.getDefaultConstructor( getter.getReturnType() ).newInstance();

			boolean ret = true;
			for ( IdMapper idMapper : ids.values() ) {
				ret &= idMapper.mapToEntityFromMap( subObj, data );
			}

			if ( ret ) {
				setter.set( obj, subObj, null );
			}

			return ret;
		}
		catch (Exception e) {
			throw new AuditException( e );
		}
	}
