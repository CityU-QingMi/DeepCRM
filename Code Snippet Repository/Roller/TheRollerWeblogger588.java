	private boolean validateRollerProperties(String ldapDc, String ldapOu, String ldapPort, String ldapHost) {
		boolean ret = false;
		
		if((ldapDc != null && !ldapDc.isEmpty())
				&& (ldapOu != null && !ldapOu.isEmpty())
				&& (ldapPort != null && !ldapPort.isEmpty())
				&& (ldapHost != null && !ldapHost.isEmpty())){
			ret = true;
		}
		
		return ret;
	}
