    public void testContainsKeyWillReturnFalseIfObjectNotFoundOnSessionMap() throws Exception {
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();
    	
        Object key = new Object();
        Object someOtherKey = new Object();
        Object value = new Object();
        
        SessionMap<Object, Object> sessionMap = new SessionMap<Object, Object>(request);
        sessionMap.put(key, value);
        
        assertFalse(sessionMap.containsKey(someOtherKey));
    }
