	@SuppressWarnings("")
	public static ListenerFactoryBeanManagerDelayedImpl fromBeanManagerReference(Object reference) {
		if ( !BeanManager.class.isInstance( reference ) ) {
			throw new IllegalArgumentException(
					"Expecting BeanManager reference that implements CDI BeanManager contract : " +
							reference
			);
		}
		return new ListenerFactoryBeanManagerDelayedImpl( (BeanManager) reference );
	}
