	protected boolean isPhysicalTableType(String tableType) {
		if ( extraPhysicalTableTypes == null ) {
			return "TABLE".equalsIgnoreCase( tableType );
		}
		else {
			if ( "TABLE".equalsIgnoreCase( tableType ) ) {
				return true;
			}
			for ( int i = 0; i < extraPhysicalTableTypes.length; i++ ) {
				if ( extraPhysicalTableTypes[i].equalsIgnoreCase( tableType ) ) {
					return true;
				}
			}
			return false;
		}
	}
