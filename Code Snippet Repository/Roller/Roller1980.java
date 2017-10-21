    public List<WeblogEntryComment> getRecentComments(int length) {
        if (length > MAX_ENTRIES) {
            length = MAX_ENTRIES;
        }
        List<WeblogEntryComment> recentComments = new ArrayList<WeblogEntryComment>();
        if (length < 1) {
            return recentComments;
        }
        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            CommentSearchCriteria csc = new CommentSearchCriteria();
            csc.setWeblog(this);
            csc.setStatus(WeblogEntryComment.ApprovalStatus.APPROVED);
            csc.setReverseChrono(true);
            csc.setMaxResults(length);
            recentComments = wmgr.getComments(csc);
        } catch (WebloggerException e) {
            log.error("ERROR: getting recent comments", e);
        }
        return recentComments;
    }
