	@Override
	@Nullable
	public org.springframework.jmx.export.metadata.ManagedResource getManagedResource(Class<?> beanClass) throws InvalidMetadataException {
		ManagedResource ann = AnnotationUtils.findAnnotation(beanClass, ManagedResource.class);
		if (ann == null) {
			return null;
		}
		Class<?> declaringClass = AnnotationUtils.findAnnotationDeclaringClass(ManagedResource.class, beanClass);
		Class<?> target = (declaringClass != null && !declaringClass.isInterface() ? declaringClass : beanClass);
		if (!Modifier.isPublic(target.getModifiers())) {
			throw new InvalidMetadataException("@ManagedResource class '" + target.getName() + "' must be public");
		}
		org.springframework.jmx.export.metadata.ManagedResource managedResource = new org.springframework.jmx.export.metadata.ManagedResource();
		AnnotationBeanUtils.copyPropertiesToBean(ann, managedResource, this.embeddedValueResolver);
		return managedResource;
	}
