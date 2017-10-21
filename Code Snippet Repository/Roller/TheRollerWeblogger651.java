    public void loadCheckboxes(List<WeblogEntryComment> comments) {
        
        List<String> allComments = new ArrayList<String>();
        List<String> spamList = new ArrayList<String>();
        
        for (WeblogEntryComment comment : comments) {
            allComments.add(comment.getId());

            if (ApprovalStatus.SPAM.equals(comment.getStatus())) {
                spamList.add(comment.getId());
            }
        }

        String[] idArray = allComments.toArray(new String[allComments.size()]);
        this.setIds(Utilities.stringArrayToString(idArray,","));
        
        spamComments = spamList.toArray(new String[spamList.size()]);
    }
