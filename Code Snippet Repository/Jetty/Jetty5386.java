    @Test
    public void test()
    {
        try
        {
            HostPort hostPort = new HostPort(_authority);
            assertThat(_authority,hostPort.getHost(),is(_expectedHost));
            
            if (_expectedPort==null)
                assertThat(_authority,hostPort.getPort(),is(0));
            else
                assertThat(_authority,hostPort.getPort(),is(Integer.valueOf(_expectedPort)));
        }
        catch (Exception e)
        {
            if (_expectedHost!=null)
                e.printStackTrace();
            assertNull(_authority,_expectedHost);
        }
    }
