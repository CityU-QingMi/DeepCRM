    @Override
    public Locale resolveLocale(Request request) {
        HttpServletRequest httpRequest = ServletUtil.getServletRequest(request).getRequest();
        ActionContext ctx = ServletActionContext.getActionContext(httpRequest);

        if (ctx == null) {
            LOG.error("Cannot obtain HttpServletRequest from [{}]", request.getClass().getName());
            throw new ConfigurationException("There is no ActionContext for current request!");
        }

        LocaleProviderFactory localeProviderFactory = ctx.getInstance(LocaleProviderFactory.class);

        return localeProviderFactory.createLocaleProvider().getLocale();
    }
