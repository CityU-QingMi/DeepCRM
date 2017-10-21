    private void transform(ContentTransformer transformer, ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
    {
        try
        {
            transformer.transform(input, finished, output);
        }
        catch (Throwable x)
        {
            _log.info("Exception while transforming " + transformer, x);
            throw x;
        }
    }
