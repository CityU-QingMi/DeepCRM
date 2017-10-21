    @Test
    public void testMD5() throws Exception
    {
        MD5 m1 = (MD5)Credential.getCredential(MD5.digest("123foo"));
        MD5 m2 = (MD5)Credential.getCredential(MD5.digest("123foo"));
        MD5 m3 = (MD5)Credential.getCredential(MD5.digest("123boo"));
        
        assertTrue(m1.equals(m2));
        assertTrue(m2.equals(m1));
        assertFalse(m3.equals(m1));
    }
