    @Test
    public void testCrypt() throws Exception
    {
        Crypt c1 = (Crypt)Credential.getCredential(Crypt.crypt("fred", "abc123"));     
        Crypt c2 = (Crypt)Credential.getCredential(Crypt.crypt("fred", "abc123"));
        
        Crypt c3 = (Crypt)Credential.getCredential(Crypt.crypt("fred", "xyz123"));
        
        Credential c4 = Credential.getCredential(Crypt.crypt("fred", "xyz123"));
        
        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));
        assertFalse(c1.equals(c3));
        assertFalse(c3.equals(c1));
        assertFalse(c3.equals(c2));
        assertTrue(c4.equals(c3));
        assertFalse(c4.equals(c1));
        
    }
