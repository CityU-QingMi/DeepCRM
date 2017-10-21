    public void convert(MessageBytes mb, boolean query)
        throws IOException
    {

        switch (mb.getType()) {
        case MessageBytes.T_STR:
            String strValue=mb.toString();
            if( strValue==null ) {
                return;
            }
            try {
                mb.setString( convert( strValue, query ));
            } catch (RuntimeException ex) {
                throw new DecodeException(ex.getMessage());
            }
            break;
        case MessageBytes.T_CHARS:
            CharChunk charC=mb.getCharChunk();
            convert( charC, query );
            break;
        case MessageBytes.T_BYTES:
            ByteChunk bytesC=mb.getByteChunk();
            convert( bytesC, query );
            break;
        }
    }
