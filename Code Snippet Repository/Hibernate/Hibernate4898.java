		public static ActionGrouping interpret(Map configurationValues) {
			// interpret the JPA settings first
			Action databaseAction = Action.interpretJpaSetting( configurationValues.get( HBM2DDL_DATABASE_ACTION ) );
			Action scriptAction = Action.interpretJpaSetting( configurationValues.get( HBM2DDL_SCRIPTS_ACTION ) );

			// if no JPA settings were specified, look at the legacy HBM2DDL_AUTO setting...
			if ( databaseAction == Action.NONE && scriptAction == Action.NONE ) {
				final Action hbm2ddlAutoAction = Action.interpretHbm2ddlSetting( configurationValues.get( HBM2DDL_AUTO ) );
				if ( hbm2ddlAutoAction != Action.NONE ) {
					databaseAction = hbm2ddlAutoAction;
				}
			}

			return new ActionGrouping( databaseAction, scriptAction );
		}
