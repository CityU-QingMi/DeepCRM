    private void runStories(int maxDynamicTableSize) throws Exception
    {
        // Find files
        File data = MavenTestingUtils.getTestResourceDir("data");
        String[] files = data.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.startsWith("story_");
            }
        });
        
        // Parse JSON
        Map<String,Object>[] stories = new Map[files.length];
        int i=0;
        for (String story : files)
            stories[i++]=(Map<String,Object>)JSON.parse(new FileReader(new File(data,story)));
        
        ByteBuffer buffer = BufferUtil.allocate(256*1024);
        
        // Encode all the requests
        encodeStories(buffer,stories,"request");

        // clear table
        BufferUtil.clearToFill(buffer);
        BufferUtil.flipToFlush(buffer,0);
        
        // Encode all the responses
        encodeStories(buffer,stories,"response");
        
    }
