	private void createCompositeTrackerMethod(CtClass managedCtClass) {
		try {
			MethodWriter.write( managedCtClass, "" +
							"public void %1$s(String name, %3$s tracker) {%n" +
							"  if (%2$s == null) { %2$s = new %4$s(); }%n" +
							"  %2$s.add(name, tracker);%n" +
							"}",
								EnhancerConstants.TRACKER_COMPOSITE_SET_OWNER,
								EnhancerConstants.TRACKER_COMPOSITE_FIELD_NAME,
								CompositeOwner.class.getName(),
								CompositeOwnerTracker.class.getName() );

			MethodWriter.write( managedCtClass, "" +
							"public void %1$s(String name) {%n" +
							"  if (%2$s != null) { %2$s.removeOwner(name); }%n" +
							"}",
					EnhancerConstants.TRACKER_COMPOSITE_CLEAR_OWNER,
					EnhancerConstants.TRACKER_COMPOSITE_FIELD_NAME );
		}
		catch (CannotCompileException cce) {
			cce.printStackTrace();
		}
	}
