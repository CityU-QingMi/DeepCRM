    @Test
    @Slow
    public void testSlowBiggest() throws Exception
    {
        _connector.setIdleTimeout(10000);
        
        File dir = MavenTestingUtils.getTargetFile("test-classes/simple");
        File biggest = new File(dir,"biggest.txt");
        try (OutputStream out = new FileOutputStream(biggest))
        {
            for (int i = 0; i < 10; i++)
            {
                try (InputStream in = new FileInputStream(new File(dir,"bigger.txt")))
                {
                    IO.copy(in,out);
                }
            }
            out.write("\nTHE END\n".getBytes(StandardCharsets.ISO_8859_1));
        }
        biggest.deleteOnExit();
        
        try (Socket socket = new Socket("localhost",_connector.getLocalPort());OutputStream out=socket.getOutputStream();InputStream in=socket.getInputStream())
        {
            socket.getOutputStream().write("GET /resource/biggest.txt HTTP/1.0\n\n".getBytes());
            
            byte[] array = new byte[102400];
            ByteBuffer buffer=null;
            while(true)
            {
                Thread.sleep(100);
                int len=in.read(array);
                if (len<0)
                    break;
                buffer=BufferUtil.toBuffer(array,0,len);
                // System.err.println(++i+": "+BufferUtil.toDetailString(buffer));
            }

            Assert.assertEquals('E',buffer.get(buffer.limit()-4));
            Assert.assertEquals('N',buffer.get(buffer.limit()-3));
            Assert.assertEquals('D',buffer.get(buffer.limit()-2));
            
        }
    }
