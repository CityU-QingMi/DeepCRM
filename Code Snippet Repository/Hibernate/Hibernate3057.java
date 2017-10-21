	@Override
	public Type resolveParameterBindType(Class clazz){
		String typename = clazz.getName();
		Type type = getTypeResolver().heuristicType( typename );
		boolean serializable = type != null && type instanceof SerializableType;
		if ( type == null || serializable ) {
			try {
				getMetamodel().entityPersister( clazz.getName() );
			}
			catch (MappingException me) {
				if ( serializable ) {
					return type;
				}
				else {
					throw new HibernateException( "Could not determine a type for class: " + typename );
				}
			}
			return getTypeHelper().entity( clazz );
		}
		else {
			return type;
		}
	}
