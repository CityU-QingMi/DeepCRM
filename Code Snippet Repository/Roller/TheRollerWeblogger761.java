	public void myPrepare() {
		if (getRemoveId() != null) {
            try {
                setTemplate(WebloggerFactory.getWeblogger().getWeblogManager()
                        .getTemplate(getRemoveId()));
            } catch (WebloggerException ex) {
                log.error("Error looking up template by id - " + getRemoveId(),
                        ex);
                addError("editPages.remove.notFound", getRemoveId());
            }
        }
	}
