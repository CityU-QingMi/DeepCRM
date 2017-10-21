	protected void setCurrentProperty(XProperty property) {
		if ( property == null ) {
			this.currentPropertyColumnOverride = null;
			this.currentPropertyJoinColumnOverride = null;
			this.currentPropertyJoinTableOverride = null;
			this.currentPropertyForeignKeyOverride = null;
		}
		else {
			this.currentPropertyColumnOverride = buildColumnOverride( property, getPath() );
			if ( this.currentPropertyColumnOverride.size() == 0 ) {
				this.currentPropertyColumnOverride = null;
			}

			this.currentPropertyJoinColumnOverride = buildJoinColumnOverride( property, getPath() );
			if ( this.currentPropertyJoinColumnOverride.size() == 0 ) {
				this.currentPropertyJoinColumnOverride = null;
			}

			this.currentPropertyJoinTableOverride = buildJoinTableOverride( property, getPath() );
			if ( this.currentPropertyJoinTableOverride.size() == 0 ) {
				this.currentPropertyJoinTableOverride = null;
			}

			this.currentPropertyForeignKeyOverride = buildForeignKeyOverride( property, getPath() );
			if ( this.currentPropertyForeignKeyOverride.size() == 0 ) {
				this.currentPropertyForeignKeyOverride = null;
			}
		}
	}
