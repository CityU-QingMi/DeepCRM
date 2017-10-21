    public void testAggregateIterator() {
        ClassLoaderUtil.AggregateIterator<String> aggr = new ClassLoaderUtil.AggregateIterator<>();

        Enumeration<String> en1 = new Enumeration<String>() {
            private Iterator<String> itt = Arrays.asList("str1", "str1", "str3", "str1").iterator();
           public boolean hasMoreElements() {
               return itt.hasNext();
           }

            public String nextElement() {
               return itt.next();
           }
       };

        Enumeration<String> en2 = new Enumeration<String>() {
            private Iterator<String> itt = Arrays.asList("str4", "str5").iterator();
           public boolean hasMoreElements() {
               return itt.hasNext();
           }

            public String nextElement() {
               return itt.next();
           }
       };


       aggr.addEnumeration(en1);
       aggr.addEnumeration(en2);

       assertTrue(aggr.hasNext());
       assertEquals("str1", aggr.next());

       assertTrue(aggr.hasNext());
       assertEquals("str3", aggr.next());

       assertTrue(aggr.hasNext());
       assertEquals("str4", aggr.next());

       assertTrue(aggr.hasNext());
       assertEquals("str5", aggr.next());

       assertFalse(aggr.hasNext());
    }
