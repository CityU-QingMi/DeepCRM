	@Override
	@SuppressWarnings("")
	public <T> Parameter<T> getParameter(String name, Class<T> type) {
		try {
			final QueryParameter parameter = getParameterMetadata().getQueryParameter( name );
			if ( !parameter.getParameterType().isAssignableFrom( type ) ) {
				throw new IllegalArgumentException(
						"The type [" + parameter.getParameterType().getName() +
								"] associated with the parameter corresponding to name [" + name +
								"] is not assignable to requested Java type [" + type.getName() + "]"
				);
			}
			return parameter;
		}
		catch ( HibernateException e ) {
			throw getExceptionConverter().convert( e );
		}
	}
