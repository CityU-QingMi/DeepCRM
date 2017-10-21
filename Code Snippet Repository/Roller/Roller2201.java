    public static WeblogEntryComment setupComment(String content,
            WeblogEntry entry) throws Exception {

        WeblogEntryComment testComment = new WeblogEntryComment();
        testComment.setName("test");
        testComment.setEmail("test");
        testComment.setUrl("test");
        testComment.setRemoteHost("foofoo");
        testComment.setContent("this is a test comment");
        testComment.setPostTime(new java.sql.Timestamp(new java.util.Date()
                .getTime()));
        testComment.setWeblogEntry(getManagedWeblogEntry(entry));
        testComment.setStatus(ApprovalStatus.APPROVED);

        // store testComment
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger()
                .getWeblogEntryManager();
        mgr.saveComment(testComment);

        // flush to db
        WebloggerFactory.getWeblogger().flush();

        // query for object
        WeblogEntryComment comment = mgr.getComment(testComment.getId());

        if (comment == null) {
            throw new WebloggerException("error setting up comment");
        }

        return comment;
    }
