	@SuppressWarnings("")
	public <T> JavaTypeDescriptor<T> getDescriptor(Class<T> cls) {
		if ( cls == null ) {
			throw new IllegalArgumentException( "Class passed to locate Java type descriptor cannot be null" );
		}

		JavaTypeDescriptor<T> descriptor = descriptorsByClass.get( cls );
		if ( descriptor != null ) {
			return descriptor;
		}

		if ( cls.isEnum() ) {
			descriptor = new EnumJavaTypeDescriptor( cls );
			descriptorsByClass.put( cls, descriptor );
			return descriptor;
		}

		// find the first "assignable" match
		for ( Map.Entry<Class,JavaTypeDescriptor> entry : descriptorsByClass.entrySet() ) {
			if ( entry.getKey().isAssignableFrom( cls ) ) {
				log.debugf( "Using  cached JavaTypeDescriptor instance for Java class [%s]", cls.getName() );
				return entry.getValue();
			}
		}

		if ( Serializable.class.isAssignableFrom( cls ) ) {
			return new SerializableTypeDescriptor( cls );
		}

		log.debugf(
				"Could not find matching JavaTypeDescriptor for requested Java class [%s]; using fallback.  " +
						"This means Hibernate does not know how to perform certain basic operations in relation to this Java type." +
						"",
				cls.getName()
		);
		checkEqualsAndHashCode( cls );

		return new FallbackJavaTypeDescriptor<T>( cls );
	}
