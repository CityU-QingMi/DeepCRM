    public String getText(String key, String defaultValue, String[] args, ValueStack stack) {
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
            return localizedTextProvider.findText(clazz, key, locale, defaultValue, args, stack);
        } else {
            return localizedTextProvider.findText(bundle, key, locale, defaultValue, args, stack);
        }

    }
