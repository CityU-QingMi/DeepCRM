    public AbstractWeblogEntriesPager(
            URLStrategy        strat,
            Weblog             weblog,
            String             locale,
            String             pageLink,
            String             entryAnchor,
            String             dateString,
            String             catName,
            List               tags,
            int                page) {
        
        this.urlStrategy = strat;
        
        this.weblog = weblog;
        this.locale = locale;
        this.pageLink = pageLink;
        this.entryAnchor = entryAnchor;
        this.dateString = dateString;
        this.catName = catName;
        
        if (tags != null) {
            this.tags = tags;
        }
        
        // make sure offset, length, and page are valid
        int maxLength = WebloggerRuntimeConfig.getIntProperty("site.pages.maxEntries");
        length = weblog.getEntryDisplayCount();
        if(length > maxLength) {
            length = maxLength;
        }
        
        if(page > 0) {
            this.page = page;
        }
        this.offset = length * page;
        
        // get a message utils instance to handle i18n of messages
        Locale viewLocale = null;
        if(locale != null) {
            String[] langCountry = locale.split("_");
            if(langCountry.length == 1) {
                viewLocale = new Locale(langCountry[0]);
            } else if(langCountry.length == 2) {
                viewLocale = new Locale(langCountry[0], langCountry[1]);
            }
        } else {
            viewLocale = weblog.getLocaleInstance();
        }
        this.messageUtils = I18nMessages.getMessages(viewLocale);
    }
