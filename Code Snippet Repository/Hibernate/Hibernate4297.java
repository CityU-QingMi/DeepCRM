	public static Class buildProxy(
			final Class persistentClass,
			final Class[] interfaces) {
		Set<Class<?>> key = new HashSet<Class<?>>();
		if ( interfaces.length == 1 ) {
			key.add( persistentClass );
		}
		key.addAll( Arrays.<Class<?>>asList( interfaces ) );

		return CACHE.findOrInsert( persistentClass.getClassLoader(), new TypeCache.SimpleKey(key), () ->
			new ByteBuddy()
			.ignore( isSynthetic().and( named( "getMetaClass" ).and( returns( td -> "groovy.lang.MetaClass".equals( td.getName() ) ) ) ) )
			.with(TypeValidation.DISABLED)
			.with(new NamingStrategy.SuffixingRandom("HibernateProxy"))
			.subclass(interfaces.length == 1 ? persistentClass : Object.class, ConstructorStrategy.Default.IMITATE_SUPER_CLASS_OPENING)
			.implement((Type[]) interfaces)
			.method(isVirtual().and(not(isFinalizer())))
			.intercept(MethodDelegation.to(ProxyConfiguration.InterceptorDispatcher.class))
			.method(nameStartsWith("$$_hibernate_").and(isVirtual()))
			.intercept(SuperMethodCall.INSTANCE)
			.defineField(ProxyConfiguration.INTERCEPTOR_FIELD_NAME, ProxyConfiguration.Interceptor.class, Visibility.PRIVATE)
			.implement(ProxyConfiguration.class)
			.intercept(FieldAccessor.ofField(ProxyConfiguration.INTERCEPTOR_FIELD_NAME).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC))
			.make()
			.load(persistentClass.getClassLoader())
			.getLoaded(), CACHE);
	}
