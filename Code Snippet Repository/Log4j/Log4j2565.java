    @Test
    public void testSize1() {
        final CyclicBuffer<Integer> buffer = new CyclicBuffer<>(Integer.class, 1);

        assertTrue(buffer.isEmpty());
        buffer.add(1);
        assertFalse(buffer.isEmpty());
        Integer[] items = buffer.removeAll();
        assertTrue("Incorrect number of items", items.length == 1);

        assertTrue(buffer.isEmpty());
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);
        items = buffer.removeAll();
        assertTrue("Incorrect number of items", items.length == 1);
        assertArrayEquals(new Integer[] { 4 }, items);
        assertTrue(buffer.isEmpty());
    }
