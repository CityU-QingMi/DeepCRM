				@Override
				public ClassLoader buildIsolatedClassLoader() {
					return new ClassLoader( NoCdiAvailableTest.class.getClassLoader() ) {
						@Override
						public Class<?> loadClass(String name) throws ClassNotFoundException {
							for ( String excludedPackage : EXCLUDED_PACKAGES ) {
								if ( name.startsWith( excludedPackage ) ) {
									throw new CdiClassLoadException( "CDI classes [" + name + "] excluded from load" );
								}
							}
							return super.loadClass( name );
						}
					};
				}
