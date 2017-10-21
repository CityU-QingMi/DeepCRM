		private ListenerImpl(Class<T> listenerClass) {
			AnnotatedType<T> annotatedType = beanManager.createAnnotatedType( listenerClass );
			this.injectionTarget = beanManager.createInjectionTarget( annotatedType );
			this.creationalContext = beanManager.createCreationalContext( null );

			this.listenerInstance = injectionTarget.produce( creationalContext );
			injectionTarget.inject( this.listenerInstance, creationalContext );

			injectionTarget.postConstruct( this.listenerInstance );
		}
