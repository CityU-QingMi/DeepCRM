	private ClassDescriptor toClassDescriptor(ClassFile classFile, ArchiveEntry entry) {
		ClassDescriptor.Categorization categorization = ClassDescriptor.Categorization.OTHER;;

		final AnnotationsAttribute visibleAnnotations = (AnnotationsAttribute) classFile.getAttribute( AnnotationsAttribute.visibleTag );
		if ( visibleAnnotations != null ) {
			if ( visibleAnnotations.getAnnotation( Entity.class.getName() ) != null
					|| visibleAnnotations.getAnnotation( MappedSuperclass.class.getName() ) != null
					|| visibleAnnotations.getAnnotation( Embeddable.class.getName() ) != null ) {
				categorization = ClassDescriptor.Categorization.MODEL;
			}
			else if ( visibleAnnotations.getAnnotation( Converter.class.getName() ) != null ) {
				categorization = ClassDescriptor.Categorization.CONVERTER;
			}
		}

		return new ClassDescriptorImpl( classFile.getName(), categorization, entry.getStreamAccess() );
	}
