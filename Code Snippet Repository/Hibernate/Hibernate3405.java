	@SuppressWarnings("")
	public static ListenerFactoryBeanManagerStandardImpl fromBeanManagerReference(Object reference) {
		if ( !BeanManager.class.isInstance( reference ) ) {
			throw new IllegalArgumentException(
					"Expecting BeanManager reference that implements CDI BeanManager contract : " +
							reference
			);
		}
		return new ListenerFactoryBeanManagerStandardImpl( (BeanManager) reference );
	}
