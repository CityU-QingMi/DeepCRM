	@Override
	public void buildCallbacksForEntity(String entityClassName, CallbackRegistrar callbackRegistrar) {
		try {
			final XClass entityXClass = reflectionManager.classForName( entityClassName );
			final Class entityClass = reflectionManager.toClass( entityXClass );
			for ( CallbackType callbackType : CallbackType.values() ) {
				if ( callbackRegistrar.hasRegisteredCallbacks( entityClass, callbackType ) ) {
					// this most likely means we have a class mapped multiple times using the hbm.xml
					// "entity name" feature
					log.debugf(
							"CallbackRegistry reported that Class [%s] already had %s callbacks registered; " +
									"assuming this means the class was mapped twice " +
									"(using hbm.xml entity-name support) - skipping subsequent registrations",
							entityClassName,
							callbackType.getCallbackAnnotation().getSimpleName()
					);
					continue;
				}
				final Callback[] callbacks = resolveCallbacks( entityXClass, callbackType, reflectionManager );
				callbackRegistrar.registerCallbacks( entityClass, callbacks );
			}
		}
		catch (ClassLoadingException e) {
			throw new MappingException( "entity class not found: " + entityClassName, e );
		}
	}
