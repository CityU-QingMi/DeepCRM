	private boolean alreadyEnhanced(CtClass managedCtClass) {
		try {
			for ( CtClass declaredInterface : managedCtClass.getInterfaces() ) {
				if ( PersistentAttributesHelper.isAssignable( declaredInterface, Managed.class.getName() ) ) {
					return true;
				}
			}
			return false;
		}
		catch ( NotFoundException e ) {
			throw new HibernateException( "Unable to transform class: " + e.getMessage() , e );
		}
	}
