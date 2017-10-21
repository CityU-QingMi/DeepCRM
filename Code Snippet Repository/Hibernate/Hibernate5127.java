	@Override
	@SuppressWarnings("")
	public void setParameterValues(Properties parameters) {
		ParameterType reader = (ParameterType) parameters.get( PARAMETER_TYPE );
		if ( reader != null ) {
			setJavaTypeDescriptor( new SerializableTypeDescriptor<T>( reader.getReturnedClass() ) );
		}
		else {
			String className = parameters.getProperty( CLASS_NAME );
			if ( className == null ) {
				throw new MappingException( "No class name defined for type: " + SerializableToBlobType.class.getName() );
			}
			try {
				setJavaTypeDescriptor( new SerializableTypeDescriptor<T>( ReflectHelper.classForName( className ) ) );
			}
			catch ( ClassNotFoundException e ) {
				throw new MappingException( "Unable to load class from " + CLASS_NAME + " parameter", e );
			}
		}
	}
