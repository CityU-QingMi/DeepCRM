    @Test
    public void testGetStringBuilderCapacityRestrictedToMax() throws Exception {
        final StringBuilder sb = ConcreteStringLayout.getStringBuilder();
        final int initialCapacity = sb.capacity();
        assertEquals("initial capacity", ConcreteStringLayout.DEFAULT_STRING_BUILDER_SIZE, sb.capacity());

        final int SMALL = 100;
        final String smallMessage = new String(new char[SMALL]);
        sb.append(smallMessage);
        assertTrue("capacity not grown", sb.capacity() == initialCapacity);
        assertEquals("length=msg length", SMALL, sb.length());

        final StringBuilder sb2 = ConcreteStringLayout.getStringBuilder();
        assertEquals("capacity unchanged", sb2.capacity(), initialCapacity);
        assertEquals("empty, ready for use", 0, sb2.length());

        final int LARGE = ConcreteStringLayout.MAX_STRING_BUILDER_SIZE * 2;
        final String largeMessage = new String(new char[LARGE]);
        sb2.append(largeMessage);
        assertTrue("capacity grown to fit msg length", sb2.capacity() >= LARGE);
        assertTrue("capacity is now greater than max length", sb2.capacity() >= ConcreteStringLayout.MAX_STRING_BUILDER_SIZE);
        assertEquals("length=msg length", LARGE, sb2.length());
        sb2.setLength(0); // set 0 before next getStringBuilder() call
        assertEquals("empty, cleared", 0, sb2.length());
        assertTrue("capacity remains very large", sb2.capacity() >= ConcreteStringLayout.MAX_STRING_BUILDER_SIZE);

        final StringBuilder sb3 = ConcreteStringLayout.getStringBuilder();
        assertEquals("capacity, trimmed to MAX_STRING_BUILDER_SIZE", ConcreteStringLayout.MAX_STRING_BUILDER_SIZE, sb3.capacity());
        assertEquals("empty, ready for use", 0, sb3.length());
    }
