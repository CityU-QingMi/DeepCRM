	@Override
	public void registerService(Manageable service, Class<? extends Service> serviceRole) {
		final String domain = service.getManagementDomain() == null
				? AvailableSettings.JMX_DEFAULT_OBJ_NAME_DOMAIN
				: service.getManagementDomain();
		final String serviceType = service.getManagementServiceType() == null
				? service.getClass().getName()
				: service.getManagementServiceType();
		try {
			final ObjectName objectName = new ObjectName(
					String.format(
							OBJ_NAME_TEMPLATE,
							domain,
							sessionFactoryName,
							serviceRole.getName(),
							serviceType
					)
			);
			registerMBean( objectName, service.getManagementBean() );
		}
		catch ( MalformedObjectNameException e ) {
			throw new HibernateException( "Unable to generate service IbjectName", e );
		}
	}
