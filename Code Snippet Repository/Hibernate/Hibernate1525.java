	public static GroupsPerOperation from(Map settings, ClassLoaderAccess classLoaderAccess) {
		GroupsPerOperation groupsPerOperation = new GroupsPerOperation();

		applyOperationGrouping( groupsPerOperation, Operation.INSERT, settings, classLoaderAccess );
		applyOperationGrouping( groupsPerOperation, Operation.UPDATE, settings, classLoaderAccess );
		applyOperationGrouping( groupsPerOperation, Operation.DELETE, settings, classLoaderAccess );
		applyOperationGrouping( groupsPerOperation, Operation.DDL, settings, classLoaderAccess );

		return groupsPerOperation;
	}
