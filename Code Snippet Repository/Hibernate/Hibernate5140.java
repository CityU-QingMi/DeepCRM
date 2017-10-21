	@Deprecated
	@SuppressWarnings({""})
	public static CompositeCustomType customComponent(
			Class<CompositeUserType> typeClass,
			Properties parameters,
			TypeScope scope) {
		try {
			CompositeUserType userType = typeClass.newInstance();
			injectParameters( userType, parameters );
			return new CompositeCustomType( userType );
		}
		catch (Exception e) {
			throw new MappingException( "Unable to instantiate custom type: " + typeClass.getName(), e );
		}
	}
