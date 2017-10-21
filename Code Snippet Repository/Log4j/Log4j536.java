    @Test
    public void testNullKeysAllowed() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");
        original.putValue("c", "cvalue");
        original.putValue("d", "dvalue");
        assertEquals(5, original.size());
        assertEquals("{3=3value, B=Bvalue, a=avalue, c=cvalue, d=dvalue}", original.toString());

        original.putValue(null, "nullvalue");
        assertEquals(6, original.size());
        assertEquals("{null=nullvalue, 3=3value, B=Bvalue, a=avalue, c=cvalue, d=dvalue}", original.toString());

        original.putValue(null, "otherNullvalue");
        assertEquals("{null=otherNullvalue, 3=3value, B=Bvalue, a=avalue, c=cvalue, d=dvalue}", original.toString());
        assertEquals(6, original.size());

        original.putValue(null, "nullvalue");
        assertEquals(6, original.size());
        assertEquals("{null=nullvalue, 3=3value, B=Bvalue, a=avalue, c=cvalue, d=dvalue}", original.toString());

        original.putValue(null, "abc");
        assertEquals(6, original.size());
        assertEquals("{null=abc, 3=3value, B=Bvalue, a=avalue, c=cvalue, d=dvalue}", original.toString());
    }
