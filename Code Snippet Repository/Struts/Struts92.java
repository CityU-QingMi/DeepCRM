    public String getText(String key, String[] args) {
        Object[] params;
        if (args != null) {
            params = args;
        } else {
            params = EMPTY_ARGS;
        }

        return localizedTextProvider.findDefaultText(key, ActionContext.getContext().getLocale(), params);
    }
