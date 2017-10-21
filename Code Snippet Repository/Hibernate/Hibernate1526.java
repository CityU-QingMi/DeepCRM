	private static void applyOperationGrouping(
			GroupsPerOperation groupsPerOperation,
			Operation operation,
			Map settings,
			ClassLoaderAccess classLoaderAccess) {
		groupsPerOperation.groupsPerOperation.put(
				operation,
				buildGroupsForOperation( operation, settings, classLoaderAccess )
		);
	}
