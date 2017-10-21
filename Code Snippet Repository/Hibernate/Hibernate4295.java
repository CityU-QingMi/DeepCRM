	@Override
	public Object intercept(Object proxy, Method thisMethod, Object[] args) throws Throwable {
		Object result;
		try {
			result = this.invoke( thisMethod, args, proxy );
		}
		catch (Throwable t) {
			throw new Exception( t.getCause() );
		}
		if ( result == INVOKE_IMPLEMENTATION ) {
			Object target = getImplementation();
			final Object returnValue;
			try {
				if ( ReflectHelper.isPublic( persistentClass, thisMethod ) ) {
					if ( !thisMethod.getDeclaringClass().isInstance( target ) ) {
						throw new ClassCastException(
								target.getClass().getName()
										+ " incompatible with "
										+ thisMethod.getDeclaringClass().getName()
						);
					}
					returnValue = thisMethod.invoke( target, args );
				}
				else {
					thisMethod.setAccessible( true );
					returnValue = thisMethod.invoke( target, args );
				}

				if ( returnValue == target ) {
					if ( returnValue.getClass().isInstance( proxy ) ) {
						return proxy;
					}
					else {
						LOG.narrowingProxy( returnValue.getClass() );
					}
				}
				return returnValue;
			}
			catch (InvocationTargetException ite) {
				throw ite.getTargetException();
			}
		}
		else {
			return result;
		}
	}
