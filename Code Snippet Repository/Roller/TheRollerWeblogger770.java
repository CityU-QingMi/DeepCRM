    public void copyFrom(Weblog dataHolder) {
        
        this.handle = dataHolder.getHandle();
        this.name = dataHolder.getName();
        this.tagline = dataHolder.getTagline();
        this.enableBloggerApi = dataHolder.getEnableBloggerApi();
        this.editorPage = dataHolder.getEditorPage();
        this.blacklist = dataHolder.getBlacklist();
        this.allowComments = dataHolder.getAllowComments();
        this.defaultAllowComments = dataHolder.getDefaultAllowComments();
        this.defaultCommentDays = ""+dataHolder.getDefaultCommentDays();
        this.moderateComments = dataHolder.getModerateComments();
        this.emailComments = dataHolder.getEmailComments();
        this.emailAddress = dataHolder.getEmailAddress();
        this.locale = dataHolder.getLocale();
        this.timeZone = dataHolder.getTimeZone();
        this.defaultPlugins = dataHolder.getDefaultPlugins();
        this.entryDisplayCount = dataHolder.getEntryDisplayCount();
        setActive(dataHolder.getActive());
        this.commentModerationRequired = dataHolder.getCommentModerationRequired();
        this.enableMultiLang = dataHolder.isEnableMultiLang();
        this.showAllLangs = dataHolder.isShowAllLangs();
        this.analyticsCode = dataHolder.getAnalyticsCode();
        setIcon(dataHolder.getIconPath());
        setAbout(dataHolder.getAbout());
        if (dataHolder.getBloggerCategory() != null) {
            bloggerCategoryId = dataHolder.getBloggerCategory().getId();
        }
        if (dataHolder.getDefaultPlugins() != null) {
            defaultPluginsArray = StringUtils.split(dataHolder.getDefaultPlugins(), ",");
        }
    }
