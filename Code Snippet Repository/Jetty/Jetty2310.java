    @Test
    public void testNullJndiName () throws Exception
    {
        try
        {
            InitialContext icontext = new InitialContext();
            Resource resource = new Resource (null,"foo");
            fail ("Null jndi name should not be permitted");
        }
        catch (NamingException e)
        {
            //expected
        }
    }
