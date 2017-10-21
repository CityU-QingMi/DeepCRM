		public ClassLoaderDelegate getHcannClassLoaderDelegate() {
			if ( hcannClassLoaderDelegate == null ) {
				hcannClassLoaderDelegate = new ClassLoaderDelegate() {
					private final  ClassLoaderService classLoaderService = getServiceRegistry().getService( ClassLoaderService.class );

					@Override
					public <T> Class<T> classForName(String className) throws ClassLoadingException {
						try {
							return classLoaderService.classForName( className );
						}
						catch (org.hibernate.boot.registry.classloading.spi.ClassLoadingException e) {
							return StandardClassLoaderDelegateImpl.INSTANCE.classForName( className );
						}
					}
				};
			}
			return hcannClassLoaderDelegate;
		}
