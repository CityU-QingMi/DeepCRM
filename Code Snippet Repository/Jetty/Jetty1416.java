    @Test
    public void testCRLF() throws Exception
    {
        HttpFields header = new HttpFields();

        header.put("name0", "value\r\n0");
        header.put("name\r\n1", "value1");
        header.put("name:2", "value:\r\n2");

        ByteBuffer buffer = BufferUtil.allocate(1024);
        BufferUtil.flipToFill(buffer);
        HttpGenerator.putTo(header,buffer);
        BufferUtil.flipToFlush(buffer,0);
        String out = BufferUtil.toString(buffer);
        assertThat(out,containsString("name0: value  0"));
        assertThat(out,containsString("name??1: value1"));
        assertThat(out,containsString("name?2: value:  2"));
    }
