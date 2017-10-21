	public BasicProxyFactoryImpl(Class superClass, Class[] interfaces) {
		if ( superClass == null && ( interfaces == null || interfaces.length < 1 ) ) {
			throw new AssertionFailure( "attempting to build proxy without any superclass or interfaces" );
		}

		Set<Class> key = new HashSet<Class>();
		if ( superClass != null ) {
			key.add( superClass );
		}
		if ( interfaces != null && interfaces.length > 0 ) {
			key.addAll( Arrays.asList( interfaces ) );
		}

		Class proxyClass = CACHE.get( key );

		if ( proxyClass == null ) {
			proxyClass = new ByteBuddy()
					.with( TypeValidation.DISABLED )
					.with( new AuxiliaryType.NamingStrategy.SuffixingRandom( "HibernateBasicProxy" ) )
					.subclass( superClass == null ? Object.class : superClass )
					.implement( interfaces == null ? NO_INTERFACES : interfaces )
					.defineField( ProxyConfiguration.INTERCEPTOR_FIELD_NAME, ProxyConfiguration.Interceptor.class, Visibility.PRIVATE )
					.method( ElementMatchers.isVirtual().and( ElementMatchers.not( ElementMatchers.isFinalizer() ) ) )
					.intercept( MethodDelegation.toField( ProxyConfiguration.INTERCEPTOR_FIELD_NAME ) )
					.implement( ProxyConfiguration.class )
					.intercept( FieldAccessor.ofField( ProxyConfiguration.INTERCEPTOR_FIELD_NAME ).withAssigner( Assigner.DEFAULT, Assigner.Typing.DYNAMIC ) )
					.make()
					.load( BasicProxyFactory.class.getClassLoader() )
					.getLoaded();
			Class previousProxy = CACHE.putIfAbsent( key, proxyClass );
			if ( previousProxy != null ) {
				proxyClass = previousProxy;
			}
		}

		this.proxyClass = proxyClass;
	}
