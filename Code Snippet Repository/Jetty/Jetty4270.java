        private String getParameterBytesAsString (String name, byte[] bytes) 
        throws UnsupportedEncodingException
        {
            //check if there is a specific encoding for the parameter
            Object ct = _params.getValue(name+CONTENT_TYPE_SUFFIX,0);
            //use default if not
            Charset contentType = _encoding;
            if (ct != null)
            {
                String tmp = MimeTypes.getCharsetFromContentType((String)ct);
                try
                {
                    contentType = (tmp == null?_encoding:Charset.forName(tmp));
                }
                catch (UnsupportedCharsetException e)
                {
                    throw new UnsupportedEncodingException(e.getMessage());
                }
            }
            
            return new String(bytes,contentType);
        }
