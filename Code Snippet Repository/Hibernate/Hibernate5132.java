		public void injectSessionFactory(SessionFactoryImplementor factory) {
			if ( this.factory != null ) {
				LOG.scopingTypesToSessionFactoryAfterAlreadyScoped( this.factory, factory );
			}
			else {
				LOG.tracev( "Scoping types to session factory {0}", factory );
				sessionFactoryUuid = factory.getUuid();
				String sfName = factory.getSettings().getSessionFactoryName();
				if ( sfName == null ) {
					final CfgXmlAccessService cfgXmlAccessService = factory.getServiceRegistry()
							.getService( CfgXmlAccessService.class );
					if ( cfgXmlAccessService.getAggregatedConfig() != null ) {
						sfName = cfgXmlAccessService.getAggregatedConfig().getSessionFactoryName();
					}
				}
				sessionFactoryName = sfName;
			}
			this.factory = factory;
		}
