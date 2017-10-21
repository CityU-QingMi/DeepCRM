    public void testGetMostCommentedWeblogs() throws Exception {        
        WeblogManager mgr = WebloggerFactory.getWeblogger().getWeblogManager();     
        List list = mgr.getMostCommentedWeblogs(null, null, 0, -1);  
        
        assertNotNull(list);
        assertEquals(2, list.size());
        
        StatCount s1 = (StatCount)list.get(0);
        assertEquals(website1.getId(), s1.getSubjectId());
        assertEquals(3L, s1.getCount());   
        assertEquals(website1.getHandle(), s1.getSubjectNameShort());
        assertEquals(website1.getHandle(), s1.getWeblogHandle());
        
        StatCount s2 = (StatCount)list.get(1);
        assertEquals(website2.getId(), s2.getSubjectId());
        assertEquals(1L, s2.getCount());   
    }
