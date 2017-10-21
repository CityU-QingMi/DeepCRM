    public void testLRU() {
        // Create cache with 3 item limit and 15 second timeout
        TestEnvironment env = new TestEnvironment();
        LRUCache2 cache = new LRUCache2(env, 3, 15000);
        
        env.time = 1000;
        cache.put("key1", "string1");
        cache.put("key2", "string2");
        cache.put("key3", "string3");
        assertNotNull(cache.get("key1"));
        assertNotNull(cache.get("key2"));
        assertNotNull(cache.get("key3"));
        
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        
        // accessing key1 and key2 will make key3 LRU
        cache.get("key1");
        cache.get("key2");
        
        // adding a forth key will push out the LRU entry
        cache.put("key4", "string4");
        assertNull(cache.get("key3"));
    }
