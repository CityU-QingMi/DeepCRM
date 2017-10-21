    @Test
    public void testStream() throws Exception
    {
        File content = testdir.getPathFile("content.txt").toFile();
        String[] results=new String[10];
        try(OutputStream out = new FileOutputStream(content);)
        {
            byte[] b= new byte[1024];
            
            for (int i=1024;i-->0;)
            {
                int index=i%10;
                Arrays.fill(b,(byte)('0'+(index)));
                out.write(b);
                out.write('\n');
                if (results[index]==null)
                    results[index]=new String(b,StandardCharsets.US_ASCII);
            }
        }
        
        long start=System.currentTimeMillis();
        String response = connector.getResponse("GET /context/stream/content.txt HTTP/1.0\r\n\r\n");
        long duration=System.currentTimeMillis()-start;
        
        assertThat("Response",response,containsString("200 OK"));
        assertThat("Response Length",response.length(),greaterThan(1024*1024));
        assertThat("Duration",duration,greaterThan(PAUSE*1024L*1024/BUFFER));
        
        for (int i=0;i<10;i++)
            assertThat(response,containsString(results[i]));
    }
