    public int validate(WeblogEntryComment comment, RollerMessages messages) {
        
        // linkback validation can be toggled at runtime, so check if it's enabled
        // if it's disabled then just return a score of 100
        if(!WebloggerRuntimeConfig.getBooleanProperty("site.trackbackVerification.enabled")) {
            return RollerConstants.PERCENT_100;
        }
        
        int ret = 0;
        LinkbackExtractor linkback = null;
        try {
            linkback = new LinkbackExtractor(
                    comment.getUrl(),
                    WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogEntryURL(
                    comment.getWeblogEntry().getWebsite(),
                    null,
                    comment.getWeblogEntry().getAnchor(),
                    true));
        } catch (MalformedURLException ignored1) {
        } catch (IOException ignored2) {}
        
        if (linkback != null && linkback.getExcerpt() != null) {
            ret = RollerConstants.PERCENT_100;
        } else {
            messages.addError("comment.validator.trackbackLinkbackMessage");
        }
        return ret;
    }
