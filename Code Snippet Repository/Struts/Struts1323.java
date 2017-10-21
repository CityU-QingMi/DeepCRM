    public void testGetBeanMap() throws Exception {
    	Bar bar = new Bar();
    	bar.setTitle("I have beer");
        
    	Foo foo = new Foo();
        foo.setALong(123);
        foo.setNumber(44);
        foo.setBar(bar);
        foo.setTitle("Hello Santa");
        foo.setUseful(true);
        
        // just do some of the 15 tests
        Map beans = ognlUtil.getBeanMap(foo);
        assertNotNull(beans);
        assertEquals(19, beans.size());
        assertEquals("Hello Santa", beans.get("title"));
        assertEquals(new Long("123"), beans.get("ALong"));
        assertEquals(new Integer("44"), beans.get("number"));
        assertEquals(bar, beans.get("bar"));
        assertEquals(Boolean.TRUE, beans.get("useful"));
    }
