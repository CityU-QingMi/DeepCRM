	public boolean authenticate(HttpServletRequest request) {
		boolean validUser = false;
		LdapContext context = null;

		String ldapDc = WebloggerConfig.getProperty("comment.authenticator.ldap.dc");
		String ldapOu = WebloggerConfig.getProperty("comment.authenticator.ldap.ou");
		String ldapPort = WebloggerConfig.getProperty("comment.authenticator.ldap.port");
		String ldapHost = WebloggerConfig.getProperty("comment.authenticator.ldap.host");
		String ldapSecurityLevel = WebloggerConfig.getProperty("comment.authenticator.ldap.securityLevel");
		
		boolean rollerPropertiesValid = validateRollerProperties(ldapDc, ldapOu, ldapPort, ldapHost);
		
		String ldapUser = request.getParameter("ldapUser");
		String ldapPass = request.getParameter("ldapPass");
		
		boolean userDataValid = validateUsernamePass(ldapUser, ldapPass);
		
		if(rollerPropertiesValid && userDataValid){
			try {
				Hashtable<String,String> env = new Hashtable<String,String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY,  
						"com.sun.jndi.ldap.LdapCtxFactory"); 
				if(ldapSecurityLevel != null 
						&& (ldapSecurityLevel.equalsIgnoreCase("none")
								|| ldapSecurityLevel.equalsIgnoreCase("simple")
								|| ldapSecurityLevel.equalsIgnoreCase("strong"))){
					env.put(Context.SECURITY_AUTHENTICATION, ldapSecurityLevel);	
				}  
				env.put(Context.SECURITY_PRINCIPAL,  getQualifedDc(ldapDc, ldapOu, ldapUser));  
				env.put(Context.SECURITY_CREDENTIALS, ldapPass);
				env.put(Context.PROVIDER_URL, "ldap://" + ldapHost + ":" + ldapPort);  
				context = new InitialLdapContext(env, null);
				validUser = true;
				LOG.info("LDAP Authentication Successful. user: " + ldapUser);
			} catch (Exception e) {
				// unexpected
				LOG.error(e);
			} finally {
				if(context != null){
					try {
						context.close();
					} catch (NamingException e) {
						LOG.error(e);
					}
				}
			}
		}
		return validUser;
	}
