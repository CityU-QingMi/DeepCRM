    public void loadCheckboxes(List<WeblogEntryComment> comments) {
        
        List<String> allComments = new ArrayList<String>();
        List<String> approvedList = new ArrayList<String>();
        List<String> spamList = new ArrayList<String>();
        
        for (WeblogEntryComment comment : comments) {
            allComments.add(comment.getId());
            
            if(ApprovalStatus.APPROVED.equals(comment.getStatus())) {
                approvedList.add(comment.getId());
            } else if(ApprovalStatus.SPAM.equals(comment.getStatus())) {
                spamList.add(comment.getId());
            }
        }
        
        // list of ids we are working on
        String[] idArray = allComments.toArray(new String[allComments.size()]);
        setIds(Utilities.stringArrayToString(idArray,","));
        
        // approved ids list
        setApprovedComments(approvedList.toArray(new String[approvedList.size()]));
        
        // spam ids list
        setSpamComments(spamList.toArray(new String[spamList.size()]));
    }
