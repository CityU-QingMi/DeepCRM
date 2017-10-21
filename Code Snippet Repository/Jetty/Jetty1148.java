    static void encodeValue(ByteBuffer buffer, boolean huffman, String value)
    {
        if (huffman)
        {
            // huffman literal value
            buffer.put((byte)0x80);
            NBitInteger.encode(buffer,7,Huffman.octetsNeeded(value));
            Huffman.encode(buffer,value);
        }
        else
        {
            // add literal assuming iso_8859_1
            buffer.put((byte)0x00);
            NBitInteger.encode(buffer,7,value.length());
            for (int i=0;i<value.length();i++)
            {
                char c=value.charAt(i);
                if (c<' '|| c>127)
                    throw new IllegalArgumentException();
                buffer.put((byte)c);
            }
        }
    }
