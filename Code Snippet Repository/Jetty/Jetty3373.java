    boolean readAvailable() throws IOException
    {
        int len=0;
        try
        {
            while(_in.isReady())
            {
                int b = _in.read();
                
                if (b<0)
                {
                    if (len>0)
                        __history.add("read "+len);
                    __history.add("read -1");
                    assertTrue(BufferUtil.isEmpty(_expected));
                    assertTrue(_eof);
                    return true;
                }
                else
                {
                    len++;
                    assertFalse(BufferUtil.isEmpty(_expected));
                    int a = 0xff & _expected.get();
                    assertThat(b,equalTo(a));
                }
            }
            __history.add("read "+len);
            assertTrue(BufferUtil.isEmpty(_expected));
        }
        catch(IOException e)
        {
            if (len>0)
                __history.add("read "+len);
            __history.add("read "+e);
            throw e;
        }
        return false;
    }
