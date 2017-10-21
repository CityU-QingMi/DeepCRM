	private void myValidate() {

		// make sure name is non-null and within proper size
		if (StringUtils.isEmpty(getNewTmplName())) {
			addError("Template.error.nameNull");
		} else if (getNewTmplName().length() > RollerConstants.TEXTWIDTH_255) {
			addError("Template.error.nameSize");
		}

		// make sure action is a valid
		if (getNewTmplAction() == null) {
			addError("Template.error.actionNull");
		}

		// check if template by that name already exists
		try {
			WeblogTemplate existingPage = WebloggerFactory.getWeblogger()
					.getWeblogManager()
					.getTemplateByName(getActionWeblog(), getNewTmplName());
			if (existingPage != null) {
				addError("pagesForm.error.alreadyExists", getNewTmplName());
			}
		} catch (WebloggerException ex) {
			log.error("Error checking for existing template", ex);
		}

	}
