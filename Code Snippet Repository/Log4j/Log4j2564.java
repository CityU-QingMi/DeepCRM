    @Test
    public void testSize0() {
        final CyclicBuffer<Integer> buffer = new CyclicBuffer<>(Integer.class, 0);

        assertTrue(buffer.isEmpty());
        buffer.add(1);
        assertTrue(buffer.isEmpty());
        Integer[] items = buffer.removeAll();
        assertTrue("Incorrect number of items", items.length == 0);

        assertTrue(buffer.isEmpty());
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);
        items = buffer.removeAll();
        assertTrue("Incorrect number of items", items.length == 0);
        assertTrue(buffer.isEmpty());
    }
