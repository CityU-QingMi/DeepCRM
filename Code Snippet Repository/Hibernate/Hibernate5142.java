	@Deprecated
	public static CustomType custom(Class<UserType> typeClass, Properties parameters, TypeScope scope) {
		try {
			UserType userType = typeClass.newInstance();
			injectParameters( userType, parameters );
			return new CustomType( userType );
		}
		catch (Exception e) {
			throw new MappingException( "Unable to instantiate custom type: " + typeClass.getName(), e );
		}
	}
