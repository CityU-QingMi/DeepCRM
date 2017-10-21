    public boolean getCommentsStillAllowed() {
        if (!WebloggerRuntimeConfig.getBooleanProperty("users.comments.enabled")) {
            return false;
        }
        if (getWebsite().getAllowComments() != null && !getWebsite().getAllowComments()) {
            return false;
        }
        if (getAllowComments() != null && !getAllowComments()) {
            return false;
        }
        boolean ret = false;
        if (getCommentDays() == null || getCommentDays() == 0) {
            ret = true;
        } else {
            // we want to use pubtime for calculating when comments expire, but
            // if pubtime isn't set (like for drafts) then just use updatetime
            Date inPubTime = getPubTime();
            if (inPubTime == null) {
                inPubTime = getUpdateTime();
            }
            
            Calendar expireCal = Calendar.getInstance(
                    getWebsite().getLocaleInstance());
            expireCal.setTime(inPubTime);
            expireCal.add(Calendar.DATE, getCommentDays());
            Date expireDay = expireCal.getTime();
            Date today = new Date();
            if (today.before(expireDay)) {
                ret = true;
            }
        }
        return ret;
    }
