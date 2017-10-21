    private void encodeName(ByteBuffer buffer, byte mask, int bits, String name, Entry entry)
    {
        buffer.put(mask);
        if (entry==null)
        {
            // leave name index bits as 0
            // Encode the name always with lowercase huffman
            buffer.put((byte)0x80);
            NBitInteger.encode(buffer,7,Huffman.octetsNeededLC(name));
            Huffman.encodeLC(buffer,name);
        }
        else
        {
            NBitInteger.encode(buffer,bits,_context.index(entry));
        }
    }
