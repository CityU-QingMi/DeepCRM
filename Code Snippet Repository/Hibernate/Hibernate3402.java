	@SuppressWarnings("")
	public static ListenerFactoryBeanManagerExtendedImpl fromBeanManagerReference(Object reference) {
		if ( !ExtendedBeanManager.class.isInstance( reference ) ) {
			throw new IllegalArgumentException(
					"Expecting BeanManager reference that implements optional ExtendedBeanManager contract : " +
							reference
			);
		}
		return new ListenerFactoryBeanManagerExtendedImpl( (ExtendedBeanManager) reference );
	}
