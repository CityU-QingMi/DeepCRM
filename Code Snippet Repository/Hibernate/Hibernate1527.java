	public static Class<?>[] buildGroupsForOperation(Operation operation, Map settings, ClassLoaderAccess classLoaderAccess) {
		final Object property = settings.get( operation.getGroupPropertyName() );

		if ( property == null ) {
			return operation == Operation.DELETE ? EMPTY_GROUPS : DEFAULT_GROUPS;
		}

		if ( property instanceof Class<?>[] ) {
			return (Class<?>[]) property;
		}

		if ( property instanceof String ) {
			String stringProperty = (String) property;
			String[] groupNames = stringProperty.split( "," );
			if ( groupNames.length == 1 && groupNames[0].equals( "" ) ) {
				return EMPTY_GROUPS;
			}

			List<Class<?>> groupsList = new ArrayList<Class<?>>(groupNames.length);
			for (String groupName : groupNames) {
				String cleanedGroupName = groupName.trim();
				if ( cleanedGroupName.length() > 0) {
					try {
						groupsList.add( classLoaderAccess.classForName( cleanedGroupName ) );
					}
					catch ( ClassLoadingException e ) {
						throw new HibernateException( "Unable to load class " + cleanedGroupName, e );
					}
				}
			}
			return groupsList.toArray( new Class<?>[groupsList.size()] );
		}

		//null is bad and excluded by instanceof => exception is raised
		throw new HibernateException( JPA_GROUP_PREFIX + operation.getGroupPropertyName() + " is of unknown type: String or Class<?>[] only");
	}
