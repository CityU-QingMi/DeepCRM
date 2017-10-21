	public static ListenerFactory buildListenerFactory(SessionFactoryOptions options) {
		final Object beanManagerRef = options.getBeanManagerReference();
		if ( beanManagerRef == null ) {
			return new ListenerFactoryStandardImpl();
		}
		else if ( ExtendedBeanManager.class.isInstance( beanManagerRef ) ) {
			return buildExtendedBeanManagerListenerFactory( beanManagerRef );
		}
		else {
			final boolean delayAccessToCdi = options.getServiceRegistry()
					.getService( ConfigurationService.class )
					.getSetting( AvailableSettings.DELAY_CDI_ACCESS, StandardConverters.BOOLEAN, false );
			if ( delayAccessToCdi ) {
				return buildDelayedBeanManagerListenerFactory( beanManagerRef );
			}
			else {
				return buildStandardBeanManagerListenerFactory( beanManagerRef );
			}
		}
	}
