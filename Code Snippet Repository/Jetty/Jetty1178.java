    @Test 
    public void testOctetsNeeded()
    {
        assertEquals(0,NBitInteger.octectsNeeded(5,10));
        assertEquals(2,NBitInteger.octectsNeeded(5,1337));
        assertEquals(1,NBitInteger.octectsNeeded(8,42));
        assertEquals(3,NBitInteger.octectsNeeded(8,1337));

        assertEquals(0,NBitInteger.octectsNeeded(6,62));
        assertEquals(1,NBitInteger.octectsNeeded(6,63));
        assertEquals(1,NBitInteger.octectsNeeded(6,64));
        assertEquals(2,NBitInteger.octectsNeeded(6,63+0x00+0x80*0x01));
        assertEquals(3,NBitInteger.octectsNeeded(6,63+0x00+0x80*0x80));
        assertEquals(4,NBitInteger.octectsNeeded(6,63+0x00+0x80*0x80*0x80));
    }
