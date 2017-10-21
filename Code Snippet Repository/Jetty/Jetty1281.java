    @Override
    public byte[] getEncodedField(HttpHeader header, String headerString, String value)
    {
        if (header!=null)
        {
            int cbl=header.getBytesColonSpace().length;
            byte[] bytes=Arrays.copyOf(header.getBytesColonSpace(), cbl+value.length()+2);
            System.arraycopy(value.getBytes(ISO_8859_1),0,bytes,cbl,value.length());
            bytes[bytes.length-2]=(byte)'\r';
            bytes[bytes.length-1]=(byte)'\n';
            return bytes;
        }

        byte[] n=headerString.getBytes(ISO_8859_1);
        byte[] v=value.getBytes(ISO_8859_1);
        byte[] bytes=Arrays.copyOf(n,n.length+2+v.length+2);
        bytes[n.length]=(byte)':';
        bytes[n.length]=(byte)' ';
        bytes[bytes.length-2]=(byte)'\r';
        bytes[bytes.length-1]=(byte)'\n';

        return bytes;
    }
