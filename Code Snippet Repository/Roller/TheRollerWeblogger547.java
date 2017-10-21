    public List<WeblogEntryCommentWrapper> getItems() {
        
        if (comments == null) {
            // calculate offset
            int offset = getPage() * length;
            
            List<WeblogEntryCommentWrapper> results = new ArrayList<WeblogEntryCommentWrapper>();
            
            Date startDate = null;
            if(sinceDays > 0) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, -1 * sinceDays);
                startDate = cal.getTime();
            }
            
            try {
                Weblogger roller = WebloggerFactory.getWeblogger();
                WeblogEntryManager wmgr = roller.getWeblogEntryManager();

                CommentSearchCriteria csc = new CommentSearchCriteria();
                csc.setWeblog(weblog);
                csc.setStartDate(startDate);
                csc.setStatus(ApprovalStatus.APPROVED);
                csc.setReverseChrono(true);
                csc.setOffset(offset);
                csc.setMaxResults(length + 1);

                List<WeblogEntryComment> commentsList = wmgr.getComments(csc);
                
                // wrap the results
                int count = 0;
                for (WeblogEntryComment comment : commentsList) {
                    if (count++ < length) {
                        results.add(WeblogEntryCommentWrapper.wrap(comment, urlStrategy));
                    } else {
                        more = true;
                    }
                }
                
            } catch (Exception e) {
                log.error("ERROR: fetching comment list", e);
            }
            
            comments = results;
        }
        
        return comments;
    }
