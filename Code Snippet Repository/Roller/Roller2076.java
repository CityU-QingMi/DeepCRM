	public boolean execute(HttpServletRequest request) {
		User ud = CustomUserRegistry.getUserDetailsFromAuthentication(request);

		if (hasNecessaryFields(ud)) {
			UserManager mgr;
			try {
				mgr = WebloggerFactory.getWeblogger().getUserManager();

				// need to give an id to the new user if none exist
				if (ud.getId() == null) {
					ud.setId(UUIDGenerator.generateUUID());
				}
				mgr.addUser(ud);

				// for some reason the User object doesn't contain a isAdmin setting
				// so it makes it difficult to add grants without that info, so setting
				// them manually here
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				for (GrantedAuthority auth : authentication.getAuthorities()) {
					if (auth.getAuthority().contains("admin") || auth.getAuthority().contains("ADMIN")) {
						mgr.grantRole("admin", ud);
					}
				}
				WebloggerFactory.getWeblogger().flush();
                return true;

            } catch (WebloggerException e) {
				log.warn("Error while auto-provisioning user from SSO.", e);
			}
		}

        return false;
	}
