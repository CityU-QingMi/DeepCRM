    public String query() {

        // load list of comments from query
        loadComments();

        // load bean data using comments list
        getBean().loadCheckboxes(getPager().getItems());

        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger()
                    .getWeblogEntryManager();

            CommentSearchCriteria csc = new CommentSearchCriteria();
            csc.setWeblog(getActionWeblog());
            csc.setSearchText(getBean().getSearchString());
            csc.setStartDate(getBean().getStartDate());
            csc.setEndDate(getBean().getEndDate());
            csc.setStatus(getBean().getStatus());
            csc.setReverseChrono(true);

            List<WeblogEntryComment> allMatchingComments = wmgr.getComments(csc);
            if (allMatchingComments.size() > COUNT) {
                setBulkDeleteCount(allMatchingComments.size());
            }

        } catch (WebloggerException ex) {
            log.error("Error looking up comments", ex);
            addError("Error looking up comments");
        }

        return LIST;
    }
