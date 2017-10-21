	public static Interceptor configuredInterceptor(Interceptor interceptor, SessionFactoryOptions options) {
		// NOTE : DO NOT return EmptyInterceptor.INSTANCE from here as a "default for the Session"
		// 		we "filter" that one out here.  The return from here should represent the
		//		explicitly configured Interceptor (if one).  Return null from here instead; Session
		//		will handle it

		if ( interceptor != null && interceptor != EmptyInterceptor.INSTANCE ) {
			return interceptor;
		}

		// prefer the SF-scoped interceptor, prefer that to any Session-scoped interceptor prototype
		if ( options.getInterceptor() != null && options.getInterceptor() != EmptyInterceptor.INSTANCE ) {
			return options.getInterceptor();
		}

		// then check the Session-scoped interceptor prototype
		if ( options.getStatelessInterceptorImplementor() != null ) {
			try {
				return options.getStatelessInterceptorImplementor().newInstance();
			}
			catch (InstantiationException | IllegalAccessException e) {
				throw new HibernateException( "Could not instantiate session-scoped SessionFactory Interceptor", e );
			}
		}

		return null;
	}
