	public static AttributeTypeDescriptor resolve(CtClass managedCtClass, CtField persistentField) throws NotFoundException {
		boolean inherited = !managedCtClass.equals( persistentField.getDeclaringClass() );
		boolean visible = persistentField.visibleFrom( managedCtClass );
		String readerName = EnhancerConstants.PERSISTENT_FIELD_READER_PREFIX + persistentField.getName();
		String writerName = EnhancerConstants.PERSISTENT_FIELD_WRITER_PREFIX + persistentField.getName();
		InheritanceMetadata inheritanceMetadata = new InheritanceMetadata( inherited, visible, readerName, writerName );

		if ( CtClass.booleanType.equals( persistentField.getType() ) ) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Boolean.TYPE );
		}
		else if ( CtClass.byteType.equals( persistentField.getType() )) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Byte.TYPE );
		}
		else if ( CtClass.charType.equals( persistentField.getType() ) ) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Character.TYPE );
		}
		else if ( CtClass.shortType.equals( persistentField.getType() ) ) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Short.TYPE );
		}
		else if ( CtClass.intType.equals( persistentField.getType() ) ) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Integer.TYPE );
		}
		else if ( CtClass.longType.equals( persistentField.getType() ) ) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Long.TYPE );
		}
		else if ( CtClass.doubleType.equals( persistentField.getType() ) ) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Double.TYPE );
		}
		else if ( CtClass.floatType.equals( persistentField.getType() ) ) {
			return new PrimitiveAttributeTypeDescriptor( inheritanceMetadata, Float.TYPE );
		}
		else {
			return new ObjectAttributeTypeDescriptor( inheritanceMetadata, persistentField.getType() );
		}
	}
