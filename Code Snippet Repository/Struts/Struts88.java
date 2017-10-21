    protected String getErrorMessage() {
        if ((namespace != null) && (namespace.trim().length() > 0)) {
            return localizedTextProvider.findDefaultText(
                    "xwork.exception.missing-package-action",
                    Locale.getDefault(),
                    new String[]{namespace, actionName});
        } else {
            return localizedTextProvider.findDefaultText(
                    "xwork.exception.missing-action",
                    Locale.getDefault(),
                    new String[]{actionName});
        }
    }
