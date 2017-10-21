	private void createClearDirtyCollectionMethod(CtClass managedCtClass) throws CannotCompileException {
		try {
			final StringBuilder body = new StringBuilder();

			body.append(
					String.format(
							"private void %1$s() {%n" +
									"  if (%2$s == null) { %2$s = new %3$s(); }%n" +
									"  %4$s lazyInterceptor = null;%n",
							EnhancerConstants.TRACKER_COLLECTION_CLEAR_NAME,
							EnhancerConstants.TRACKER_COLLECTION_NAME,
							COLLECTION_TRACKER_IMPL,
							LazyAttributeLoadingInterceptor.class.getName()
					)
			);

			if ( PersistentAttributesHelper.isAssignable( managedCtClass, PersistentAttributeInterceptable.class.getName() ) ) {
				body.append(
						String.format(
										"  if(%1$s != null && %1$s instanceof %2$s) lazyInterceptor = (%2$s) %1$s;%n%n",
								EnhancerConstants.INTERCEPTOR_FIELD_NAME,
								LazyAttributeLoadingInterceptor.class.getName()
						)
				);
			}

			for ( CtField ctField : collectCollectionFields( managedCtClass ) ) {
				body.append(
						String.format(
									"  // collection field [%1$s]%n" +
									"  if (lazyInterceptor == null || lazyInterceptor.isAttributeLoaded(\"%1$s\")) {%n" +
									"    if (%1$s == null) { %2$s.add(\"%1$s\", -1); }%n" +
									"    else { %2$s.add(\"%1$s\", %1$s.size()); }%n" +
									"  }%n%n",
								ctField.getName(),
								EnhancerConstants.TRACKER_COLLECTION_NAME
						)
				);
			}
			body.append( "}" );

			MethodWriter.write( managedCtClass, body.toString() );
		}
		catch (CannotCompileException cce) {
			cce.printStackTrace();
		}
	}
