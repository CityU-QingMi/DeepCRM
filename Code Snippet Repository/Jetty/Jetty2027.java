    private Properties sendRequest(Properties request)
        throws Exception
    {
        ByteArrayOutputStream reqStream = null;
        ByteArrayInputStream resStream = null;
        Properties response = null;
    
        try {
            reqStream = new ByteArrayOutputStream();
            request.storeToXML(reqStream,null);
            
            ContentResponse r3sponse = _client.POST(_url)
                .header("Connection","close")
                .content(new BytesContentProvider(reqStream.toByteArray()))
                .send();
            
                        
            if (r3sponse.getStatus() == HttpStatus.OK_200)
            {
                response = new Properties();
                resStream = new ByteArrayInputStream(r3sponse.getContent());
                response.loadFromXML(resStream);               
            }
        }
        finally
        {
            try
            {
                if (reqStream != null)
                    reqStream.close();
            }
            catch (IOException ex)
            {
                LOG.ignore(ex);
            }
            
            try
            {
                if (resStream != null)
                    resStream.close();
            }
            catch (IOException ex)
            {
                LOG.ignore(ex);
            }
        }
        
        return response;    
    }
