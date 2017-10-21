    public void testBlacklistCommentValidator() {
        RollerMessages msgs = new RollerMessages();
        WeblogEntryComment comment = createEmptyComment();
       
        comment.setContent("nice friendly stuff"); 
        assertEquals(100, mgr.validateComment(comment, msgs));

        comment.setContent("blah blah www.myblacklistedsite.com blah");
        assertTrue(mgr.validateComment(comment, msgs) != 100);
    }
