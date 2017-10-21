    public String getText(String key, List<?> args) {
        Object[] params;
        if (args != null) {
            params = args.toArray();
        } else {
            params = EMPTY_ARGS;
        }

        return localizedTextProvider.findDefaultText(key, ActionContext.getContext().getLocale(), params);
    }
