	private void doPermissionCheckInContext(PermissionCheckEntityInformation entityInformation, PermissibleAction action) {
		final Policy policy = Policy.getPolicy();
		final Principal[] principals = getCallerPrincipals();

		final CodeSource codeSource = entityInformation.getEntity().getClass().getProtectionDomain().getCodeSource();
		final ProtectionDomain pd = new ProtectionDomain( codeSource, null, null, principals );

		// the action is known as 'method name' in JACC
		final EJBMethodPermission jaccPermission = new EJBMethodPermission(
				entityInformation.getEntityName(),
				action.getImpliedActions()[0],
				null,
				null
		);

		if ( ! policy.implies( pd, jaccPermission) ) {
			throw new SecurityException(
					String.format(
							"JACC denied permission to [%s.%s] for [%s]",
							entityInformation.getEntityName(),
							action.getImpliedActions()[0],
							join( principals )
					)
			);
		}
	}
