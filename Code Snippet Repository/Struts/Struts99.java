    public String getText(String key, String defaultValue, List<?> args, ValueStack stack) {
        Object[] argsArray = ((args != null) ? args.toArray() : null);
        Locale locale;
        if (stack == null){
        	locale = getLocale();
        }else{
        	locale = (Locale) stack.getContext().get(ActionContext.LOCALE);
        }
        if (locale == null) {
            locale = getLocale();
        }
        if (clazz != null) {
            return localizedTextProvider.findText(clazz, key, locale, defaultValue, argsArray, stack);
        } else {
            return localizedTextProvider.findText(bundle, key, locale, defaultValue, argsArray, stack);
        }
    }
