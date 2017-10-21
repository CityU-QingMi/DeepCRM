    public void testTriggerSort() {
        
        // build trigger in expected sort order
        Trigger t1 = newTrigger().withIdentity("a").build();
        Trigger t2 = newTrigger().withIdentity("b").build();
        Trigger t3 = newTrigger().withIdentity("c").build();
        Trigger t4 = newTrigger().withIdentity("a", "a").build();
        Trigger t5 = newTrigger().withIdentity("a", "b").build();
        Trigger t6 = newTrigger().withIdentity("a", "c").build();

        List<Trigger> ts = new LinkedList<Trigger>();
        // add triggers to list in somewhat randomized order
        ts.add(t5);
        ts.add(t6);
        ts.add(t4);
        ts.add(t3);
        ts.add(t1);
        ts.add(t2);

        // sort the list
        Collections.sort(ts);
        
        // check the order of the list
        assertEquals(t1, ts.get(0));
        assertEquals(t2, ts.get(1));
        assertEquals(t3, ts.get(2));
        assertEquals(t4, ts.get(3));
        assertEquals(t5, ts.get(4));
        assertEquals(t6, ts.get(5));
    }
