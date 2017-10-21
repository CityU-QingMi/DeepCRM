    public void testCommentCRUD() throws Exception {
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        WeblogEntryComment comment = new WeblogEntryComment();
        comment.setName("test");
        comment.setEmail("test");
        comment.setUrl("test");
        comment.setRemoteHost("foofoo");
        comment.setContent("this is a test comment");
        comment.setPostTime(new java.sql.Timestamp(new java.util.Date().getTime()));
        comment.setWeblogEntry(TestUtils.getManagedWeblogEntry(testEntry));
        comment.setStatus(ApprovalStatus.APPROVED);
        
        // create a comment
        mgr.saveComment(comment);
        String id = comment.getId();
        TestUtils.endSession(true);
        
        // make sure comment was created
        comment = mgr.getComment(id);
        assertNotNull(comment);
        assertEquals("this is a test comment", comment.getContent());
        
        // update a comment
        comment.setContent("testtest");
        mgr.saveComment(comment);
        TestUtils.endSession(true);
        
        // make sure comment was updated
        comment = mgr.getComment(id);
        assertNotNull(comment);
        assertEquals("testtest", comment.getContent());
        
        // delete a comment
        mgr.removeComment(comment);
        TestUtils.endSession(true);
        
        // make sure comment was deleted
        comment = mgr.getComment(id);
        assertNull(comment);
    }
