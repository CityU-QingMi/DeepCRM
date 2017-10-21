    public void testEntryEditors() throws Exception {
    	log.debug("Start");
        
        UIPluginManager pmgr = RollerContext.getUIPluginManager();
        
        // test getEditors() list
        assertEquals(2, pmgr.getWeblogEntryEditors().size());
        
        // test getting a single editor
        assertEquals("editor-text.jsp", pmgr.getWeblogEntryEditor("TextEditor").getId());
        
        // make sure we return default editor if editor id is not found
        assertEquals("editor-text.jsp", pmgr.getWeblogEntryEditor(null).getId());
        
    	log.debug("End");
    }
