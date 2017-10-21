	public RevisionInfoConfiguration(GlobalConfiguration globalCfg) {
		this.globalCfg = globalCfg;
		if ( globalCfg.isUseRevisionEntityWithNativeId() ) {
			revisionInfoEntityName = "org.hibernate.envers.DefaultRevisionEntity";
		}
		else {
			revisionInfoEntityName = "org.hibernate.envers.enhanced.SequenceIdRevisionEntity";
		}
		revisionInfoIdData = new PropertyData( "id", "id", "field", null );
		revisionInfoTimestampData = new PropertyData( "timestamp", "timestamp", "field", null );
		modifiedEntityNamesData = new PropertyData( "modifiedEntityNames", "modifiedEntityNames", "field", null );
		revisionInfoTimestampType = new LongType();

		revisionPropType = "integer";
	}
