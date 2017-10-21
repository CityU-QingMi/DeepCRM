    public void copyTo(Weblog dataHolder) {
        dataHolder.setName(this.name);
        dataHolder.setTagline(this.tagline);
        dataHolder.setEnableBloggerApi(this.enableBloggerApi);
        dataHolder.setEditorPage(this.editorPage);
        dataHolder.setBlacklist(this.blacklist);
        dataHolder.setAllowComments(this.allowComments);
        dataHolder.setDefaultAllowComments(this.defaultAllowComments);
        dataHolder.setModerateComments(this.moderateComments);
        dataHolder.setEmailComments(this.emailComments);
        dataHolder.setEmailAddress(this.emailAddress);
        dataHolder.setLocale(this.locale);
        dataHolder.setTimeZone(this.timeZone);
        dataHolder.setDefaultPlugins(StringUtils.join(this.defaultPluginsArray, ","));
        dataHolder.setEntryDisplayCount(this.entryDisplayCount);
        dataHolder.setActive(this.getActive());
        dataHolder.setCommentModerationRequired(this.commentModerationRequired);
        dataHolder.setEnableMultiLang(this.enableMultiLang);
        dataHolder.setShowAllLangs(this.showAllLangs);
        dataHolder.setIconPath(getIcon());
        dataHolder.setAbout(getAbout());
        dataHolder.setAnalyticsCode(this.analyticsCode);
        dataHolder.setDefaultCommentDays(Integer.parseInt(this.defaultCommentDays));
    }
