		private JaccPermissionDeclarations getJaccPermissions(String jaccContextId) {
			if ( jaccPermissionsByContextId == null ) {
				jaccPermissionsByContextId = new HashMap<>();
			}

			JaccPermissionDeclarations jaccPermissions = jaccPermissionsByContextId.get( jaccContextId );
			if ( jaccPermissions == null ) {
				jaccPermissions = new JaccPermissionDeclarations( jaccContextId );
				jaccPermissionsByContextId.put( jaccContextId, jaccPermissions );
			}
			return jaccPermissions;
		}
