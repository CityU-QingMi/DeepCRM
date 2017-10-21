			public JdbcContextDelayedDropImpl(ServiceRegistry serviceRegistry) {
				this.serviceRegistry = serviceRegistry;
				this.jdbcServices = serviceRegistry.getService( JdbcServices.class );
				this.jdbcConnectionAccess = jdbcServices.getBootstrapJdbcConnectionAccess();
				if ( jdbcConnectionAccess == null ) {
					// todo : log or error?
					throw new SchemaManagementException(
							"Could not build JDBC Connection context to drop schema on SessionFactory close"
					);
				}
			}
