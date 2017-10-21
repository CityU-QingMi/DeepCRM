	public String execute() {
		try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
			allCategories = wmgr.getWeblogCategories(getActionWeblog());
		} catch (WebloggerException ex) {
			log.error("Error building categories list", ex);
			addError("Error building categories list");
		}

		return LIST;
	}
