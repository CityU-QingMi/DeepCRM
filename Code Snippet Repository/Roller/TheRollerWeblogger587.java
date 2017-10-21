	private boolean validateUsernamePass(String ldapUser, String ldapPass) {
		boolean ret = false;
		
		if((ldapUser != null && !ldapUser.isEmpty()) 
				&& (ldapPass != null && !ldapPass.isEmpty())){
			ret = true;
		}
		
		return ret;
	}
