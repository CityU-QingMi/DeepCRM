    private void encodeStories(ByteBuffer buffer,Map<String,Object>[] stories, String type) throws Exception
    {
        for (Map<String,Object> story : stories)
        {
            if (type.equals(story.get("context")))
            {
                HpackEncoder encoder = new HpackEncoder(_maxDynamicTableSize,_maxDynamicTableSize);
                
                // System.err.println(story);
                Object[] cases = (Object[])story.get("cases");
                for (Object c : cases)
                {
                    // System.err.println("  "+c);
                    Object[] headers = (Object[])((Map<String,Object>)c).get("headers");
                    // System.err.println("    "+headers);
                    HttpFields fields = new HttpFields();
                    for (Object header:headers)
                    {
                        Map<String,String> h = (Map<String,String>)header;
                        Map.Entry<String, String> e = h.entrySet().iterator().next();
                        fields.add(e.getKey(),e.getValue());
                        _unencodedSize+=e.getKey().length()+e.getValue().length();
                        
                    }

                    BufferUtil.clearToFill(buffer);
                    encoder.encode(buffer,new MetaData(HttpVersion.HTTP_2,fields));
                    BufferUtil.flipToFlush(buffer,0);
                    _encodedSize+=buffer.remaining();
                    
                }
            }
        }

    }
