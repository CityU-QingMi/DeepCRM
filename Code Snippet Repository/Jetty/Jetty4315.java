    public ContentMetadata getResponseMetadata(Response response) throws Exception
    {
        long size = response.getContentBytes().length;

        String contentEncoding = response.get("Content-Encoding");

        ByteArrayInputStream bais = null;
        InputStream in = null;
        DigestOutputStream digester = null;
        ByteArrayOutputStream uncompressedStream = null;
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            bais = new ByteArrayInputStream(response.getContentBytes());

            if (contentEncoding == null)
            {
                LOG.debug("No response content-encoding");
                in = new PassThruInputStream(bais);
            }
            else if (contentEncoding.contains(GzipHandler.GZIP))
            {
                in = new GZIPInputStream(bais);
            }
            else if (contentEncoding.contains(GzipHandler.DEFLATE))
            {
                in = new InflaterInputStream(bais,new Inflater(true));
            }
            else
            {
                assertThat("Unexpected response content-encoding", contentEncoding, isEmptyOrNullString());
            }
            
            uncompressedStream = new ByteArrayOutputStream((int)size); 

            digester = new DigestOutputStream(uncompressedStream,digest);
            IO.copy(in,digester);
            
            byte output[] = uncompressedStream.toByteArray();
            String actualSha1Sum = Hex.asHex(digest.digest());
            return new ContentMetadata(output.length,actualSha1Sum);
        }
        finally
        {
            IO.close(digester);
            IO.close(in);
            IO.close(bais);
            IO.close(uncompressedStream);
        }
    }
