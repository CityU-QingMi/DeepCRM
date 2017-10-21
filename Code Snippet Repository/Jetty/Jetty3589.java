    @Test
    public void test9_4()
    {
        try
        {
            String get=connector.getResponse("GET /R1 HTTP/1.0\n"+"Host: localhost\n"+"\n");

            checkContains(get,0,"HTTP/1.1 200","GET");
            checkContains(get,0,"Content-Type: text/html","GET _content");
            checkContains(get,0,"<html>","GET body");

            String head=connector.getResponse("HEAD /R1 HTTP/1.0\n"+"Host: localhost\n"+"\n");
            checkContains(head,0,"HTTP/1.1 200","HEAD");
            checkContains(head,0,"Content-Type: text/html","HEAD _content");
            assertEquals("HEAD no body",-1,head.indexOf("<html>"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assertTrue(false);
        }
    }
