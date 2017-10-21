	@Override
	@SuppressWarnings({ "" })
	public <T> Parameter<T> getParameter(String name, Class<T> type) {
		entityManager.checkOpen( false );
		Parameter parameter = locateParameterByName( name );
		if ( type.isAssignableFrom( parameter.getParameterType() ) ) {
			return parameter;
		}
		throw new IllegalArgumentException(
				"Named parameter [" + name + "] type is not assignanle to request type ["
						+ type.getName() + "]"
		);
	}
