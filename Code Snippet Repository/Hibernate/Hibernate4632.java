	@Override
	public void addPermission(GrantedPermission permissionDeclaration) {
		// todo : do we need to wrap these PolicyConfiguration calls in privileged actions like we do during permission checks?

		if ( policyConfiguration == null ) {
			policyConfiguration = locatePolicyConfiguration( contextId );
		}

		for ( String grantedAction : permissionDeclaration.getPermissibleAction().getImpliedActions() ) {
			final EJBMethodPermission permission = new EJBMethodPermission(
					permissionDeclaration.getEntityName(),
					grantedAction,
					null, // interfaces
					null // arguments
			);

			log.debugf( "Adding permission [%s] to role [%s]", grantedAction, permissionDeclaration.getRole() );
			try {
				policyConfiguration.addToRole( permissionDeclaration.getRole(), permission );
			}
			catch (PolicyContextException pce) {
				throw new HibernateException( "policy context exception occurred", pce );
			}
		}
	}
