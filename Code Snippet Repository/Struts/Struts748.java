    @Override
    protected String getErrorMessage() {
        if ((namespace != null) && (namespace.trim().length() > 0)) {
            String contextPath = ServletActionContext.getRequest().getContextPath();
            return localizedTextProvider.findDefaultText(
                    "struts.exception.missing-package-action.with-context",
                    Locale.getDefault(),
                    new String[]{namespace, actionName, contextPath}
            );
        } else {
            return super.getErrorMessage();
        }
    }
