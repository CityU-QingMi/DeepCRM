	public void addDescriptor(JavaTypeDescriptor descriptor) {
		JavaTypeDescriptor old = addDescriptorInternal( descriptor );
		if ( old != null ) {
			log.debugf(
					"JavaTypeDescriptorRegistry entry replaced : %s -> %s (was %s)",
					descriptor.getJavaTypeClass(),
					descriptor,
					old
			);
		}
	}
