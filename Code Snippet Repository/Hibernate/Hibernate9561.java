	private void dump(PersistentClass entityBinding, TheEntity theEntity) {
		final Iterator propertyBindingIterator = entityBinding.getPropertyClosureIterator();
		while ( propertyBindingIterator.hasNext() ) {
			final Property propertyBinding = (Property) propertyBindingIterator.next();
			final JavaTypeDescriptor javaTypeDescriptor = ( (AbstractStandardBasicType) propertyBinding.getType() ).getJavaTypeDescriptor();

			System.out.println(
					String.format(
							"%s (%s) -> %s",
							propertyBinding.getName(),
							javaTypeDescriptor.getJavaTypeClass().getSimpleName(),
							javaTypeDescriptor.toString( propertyBinding.getGetter( TheEntity.class ).get( theEntity ) )
					)
			);
		}
	}
