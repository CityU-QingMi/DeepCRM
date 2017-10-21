	private void createCollectionDirtyCheckMethod(CtClass managedCtClass) {
		try {
			final StringBuilder body = new StringBuilder();

			body.append(
					String.format(
									"private boolean %1$s() {%n" +
									"  if (%2$s == null) { return false; }%n%n",
							EnhancerConstants.TRACKER_COLLECTION_CHANGED_NAME,
							EnhancerConstants.TRACKER_COLLECTION_NAME
					)
			);

			for ( CtField ctField : collectCollectionFields( managedCtClass ) ) {
				body.append(
						String.format(
										"  // collection field [%1$s]%n" +
										"  if (%1$s == null && %2$s.getSize(\"%1$s\") != -1) { return true; }%n" +
										"  if (%1$s != null && %2$s.getSize(\"%1$s\") != %1$s.size()) { return true; }%n%n",
								ctField.getName(),
								EnhancerConstants.TRACKER_COLLECTION_NAME
						)
				);
			}
			body.append( "  return false;%n}" );

			MethodWriter.write( managedCtClass, body.toString() );
		}
		catch (CannotCompileException cce) {
			cce.printStackTrace();
		}
	}
