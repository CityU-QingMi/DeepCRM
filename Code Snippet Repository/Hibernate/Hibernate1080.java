	private void createCollectionDirtyCheckGetFieldsMethod(CtClass managedCtClass) {
		try {
			final StringBuilder body = new StringBuilder();

			body.append(
					String.format(
									"private void %1$s(%3$s tracker) {%n" +
									"  if (%2$s == null) { return; }%n%n",
							EnhancerConstants.TRACKER_COLLECTION_CHANGED_FIELD_NAME,
							EnhancerConstants.TRACKER_COLLECTION_NAME,
							DirtyTracker.class.getName()
					)
			);

			for ( CtField ctField : collectCollectionFields( managedCtClass ) ) {
				body.append(
						String.format(
										"  // Collection field [%1$s]%n" +
										"  if (%1$s == null && %2$s.getSize(\"%1$s\") != -1) { tracker.add(\"%1$s\"); }%n" +
										"  if (%1$s != null && %2$s.getSize(\"%1$s\") != %1$s.size()) { tracker.add(\"%1$s\"); }%n%n",
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
