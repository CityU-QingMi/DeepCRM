    static public void decodeRFC4648URL (String encoded, ByteArrayOutputStream bout)
    {
        if (encoded==null)
            return;
        
        if (bout == null)
            throw new IllegalArgumentException("No outputstream for decoded bytes");
        
        int ci=0;
        byte nibbles[] = new byte[4];
        int s=0;
  
        while (ci<encoded.length())
        {
            char c=encoded.charAt(ci++);

            if (c==__pad)
                break;

            if (Character.isWhitespace(c))
                continue;

            byte nibble=__rfc4648urlNibbles[c];
            if (nibble<0)
                throw new IllegalArgumentException("Not B64 encoded");

            nibbles[s++]=__rfc4648urlNibbles[c];

            switch(s)
            {
                case 1:
                    break;
                case 2:
                    bout.write(nibbles[0]<<2|nibbles[1]>>>4);
                    break;
                case 3:
                    bout.write(nibbles[1]<<4|nibbles[2]>>>2);
                    break;
                case 4:
                    bout.write(nibbles[2]<<6|nibbles[3]);
                    s=0;
                    break;
            }

        }

        return;
    }
