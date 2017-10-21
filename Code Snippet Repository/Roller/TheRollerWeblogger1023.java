    public void testTimeout() {
        // Create cache with 100 item limit and 15 second timeout
        TestEnvironment env = new TestEnvironment();
        LRUCache2 cache = new LRUCache2(env, 100, 15000);
        
        env.time = 1000;
        cache.put("key1", "string1");
        cache.put("key2", "string2");
        cache.put("key3", "string3");
        assertNotNull(cache.get("key1"));
        assertNotNull(cache.get("key2"));
        assertNotNull(cache.get("key3"));
        
        env.time = 16000;
        assertNull(cache.get("key1"));
        assertNull(cache.get("key2"));
        assertNull(cache.get("key3"));
    }
