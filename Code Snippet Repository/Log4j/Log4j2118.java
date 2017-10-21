    @Test
    public void testToString() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "avalue");
        original.putValue("a2", "bvalue");
        original.putValue("B", "Bvalue");
        original.putValue("C", "Cvalue");
        original.putValue("3", "3value");
        assertEquals("{3=3value, B=Bvalue, C=Cvalue, a=avalue, a2=bvalue}", original.toString());
    }
