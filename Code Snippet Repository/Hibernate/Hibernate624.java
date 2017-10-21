	private void addJaccPermissions(Map<String, JaccPermissionDeclarations> jaccPermissionsByContextId) {
		if ( jaccPermissionsByContextId == null ) {
			return;
		}

		if ( this.jaccPermissionsByContextId == null ) {
			this.jaccPermissionsByContextId = new HashMap<String, JaccPermissionDeclarations>();
		}

		for ( Map.Entry<String, JaccPermissionDeclarations> incomingEntry : jaccPermissionsByContextId.entrySet() ) {
			JaccPermissionDeclarations permissions = jaccPermissionsByContextId.get( incomingEntry.getKey() );
			if ( permissions == null ) {
				permissions = new JaccPermissionDeclarations( incomingEntry.getKey() );
				this.jaccPermissionsByContextId.put( incomingEntry.getKey(), permissions );
			}

			permissions.addPermissionDeclarations( incomingEntry.getValue().getPermissionDeclarations() );
		}
	}
