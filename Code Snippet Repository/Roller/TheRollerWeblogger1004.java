    public void testGetMostCommentedWeblogEntries() throws Exception {
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();      
        List list = mgr.getMostCommentedWeblogEntries(null, null, null, 0, -1);
        
        assertNotNull(list);
        assertEquals(3, list.size());
        
        StatCount s1 = (StatCount)list.get(0);
        assertEquals(2L, s1.getCount()); 
        assertEquals(entry11.getAnchor(), s1.getSubjectNameShort());
        assertEquals(entry11.getWebsite().getHandle(), s1.getWeblogHandle());
               
        StatCount s2 = (StatCount)list.get(1);
        assertEquals(1L, s2.getCount());   
    }
