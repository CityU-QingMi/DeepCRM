    @Test
    public void testGoodPost() throws Exception
    {
        StringBuilder req = new StringBuilder();
        req.append("POST /post HTTP/1.1\r\n");
        req.append("Host: localhost\r\n");
        req.append("Transfer-Encoding: chunked\r\n");
        req.append("\r\n");
        req.append("6\r\n");
        req.append("Hello ");
        req.append("\r\n");
        req.append("7\r\n");
        req.append("World!\n");
        req.append("\r\n");
        req.append("0\r\n");
        req.append("\r\n");

        String resp = connector.getResponse(req.toString());

        assertThat("resp", resp, containsString("HTTP/1.1 200 OK"));
        assertThat("resp", resp, containsString("chunked"));
        assertThat("resp", resp, containsString("read 6"));
        assertThat("resp", resp, containsString("read 7"));
        assertThat("resp", resp, containsString("\r\n0\r\n"));

        assertThat(ex0.get(),nullValue());
        assertThat(ex1.get(),nullValue());
    }
