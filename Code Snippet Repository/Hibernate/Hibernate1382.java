	@SuppressWarnings({ "" })
	public void setProxy(Proxy proxy) {
		if ( proxy != null ) {
			lazy = proxy.lazy();
			if ( !lazy ) {
				proxyClass = null;
			}
			else {
				final ReflectionManager reflectionManager = context.getBuildingOptions().getReflectionManager();
				if ( AnnotationBinder.isDefault( reflectionManager.toXClass( proxy.proxyClass() ), context ) ) {
					proxyClass = annotatedClass;
				}
				else {
					proxyClass = reflectionManager.toXClass( proxy.proxyClass() );
				}
			}
		}
		else {
			lazy = true; //needed to allow association lazy loading.
			proxyClass = annotatedClass;
		}
	}
