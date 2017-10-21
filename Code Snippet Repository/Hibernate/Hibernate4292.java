	protected final Object invoke(Method method, Object[] args, Object proxy) throws Throwable {
		String methodName = method.getName();
		int params = args.length;

		if ( params == 0 ) {
			if ( "writeReplace".equals( methodName ) ) {
				return getReplacement();
			}
			else if ( !overridesEquals && "hashCode".equals( methodName ) ) {
				return System.identityHashCode( proxy );
			}
			else if ( isUninitialized() && method.equals( getIdentifierMethod ) ) {
				return getIdentifier();
			}
			else if ( "getHibernateLazyInitializer".equals( methodName ) ) {
				return this;
			}
		}
		else if ( params == 1 ) {
			if ( !overridesEquals && "equals".equals( methodName ) ) {
				return args[0] == proxy;
			}
			else if ( method.equals( setIdentifierMethod ) ) {
				initialize();
				setIdentifier( (Serializable) args[0] );
				return INVOKE_IMPLEMENTATION;
			}
		}

		//if it is a property of an embedded component, invoke on the "identifier"
		if ( componentIdType != null && componentIdType.isMethodOf( method ) ) {
			return method.invoke( getIdentifier(), args );
		}

		// otherwise:
		return INVOKE_IMPLEMENTATION;

	}
