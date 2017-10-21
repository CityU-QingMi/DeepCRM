    public List<WeblogEntryComment> getComments(boolean ignoreSpam, boolean approvedOnly) {
        List<WeblogEntryComment> list = new ArrayList<WeblogEntryComment>();
        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

            CommentSearchCriteria csc = new CommentSearchCriteria();
            csc.setWeblog(getWebsite());
            csc.setEntry(this);
            csc.setStatus(approvedOnly ? WeblogEntryComment.ApprovalStatus.APPROVED : null);
            return wmgr.getComments(csc);
        } catch (WebloggerException alreadyLogged) {}
        return list;
    }
