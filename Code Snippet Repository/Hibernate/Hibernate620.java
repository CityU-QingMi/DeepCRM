	public JaccPermissionDeclarations getOrCreateJaccPermissions(String contextId) {
		if ( jaccPermissionsByContextId == null ) {
			jaccPermissionsByContextId = new HashMap<String, JaccPermissionDeclarations>();
		}

		JaccPermissionDeclarations jaccPermission = jaccPermissionsByContextId.get( contextId );
		if ( jaccPermission == null ) {
			jaccPermission = new JaccPermissionDeclarations( contextId );
		}
		jaccPermissionsByContextId.put( contextId, jaccPermission );

		return jaccPermission;
	}
