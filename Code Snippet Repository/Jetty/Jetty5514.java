    @Test
    public void testRemoveValue_FromEmpty()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        // Setup the key
        mm.putValues(key,new String[0]);
        assertMapSize(mm,1);
        assertEmptyValues(mm,key);

        // Remove a value that isn't in the underlying values
        mm.removeValue(key,"jar");
        assertMapSize(mm,1);
        assertEmptyValues(mm,key);
    }
