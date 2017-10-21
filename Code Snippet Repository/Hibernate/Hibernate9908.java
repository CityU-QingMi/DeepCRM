	protected boolean checkUsingModifiedFlag(Audited aud) {
		// HHH-10468
		if ( globalCfg.hasSettingForUsingModifiedFlag() ) {
			// HHH-10468
			// Modify behavior so that if the global setting has been set by user properties, then
			// the audit behavior should be a disjunction between the global setting and the field
			// annotation.  This allows the annotation to take precedence when the global value is
			// false and for the global setting to take precedence when true.
			return globalCfg.isGlobalWithModifiedFlag() || aud.withModifiedFlag();
		}
		// no global setting enabled, use the annotation's value only.
		return aud.withModifiedFlag();
	}
