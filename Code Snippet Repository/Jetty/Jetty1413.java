    @Test
    public void testPutTo() throws Exception
    {
        HttpFields header = new HttpFields();

        header.put("name0", "value0");
        header.put("name1", "value:A");
        header.add("name1", "value:B");
        header.add("name2", "");

        ByteBuffer buffer=BufferUtil.allocate(1024);
        BufferUtil.flipToFill(buffer);
        HttpGenerator.putTo(header,buffer);
        BufferUtil.flipToFlush(buffer,0);
        String result=BufferUtil.toString(buffer);

        assertThat(result,Matchers.containsString("name0: value0"));
        assertThat(result,Matchers.containsString("name1: value:A"));
        assertThat(result,Matchers.containsString("name1: value:B"));
    }
