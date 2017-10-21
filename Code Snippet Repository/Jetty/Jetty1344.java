    public static HttpVersion lookAheadGet(byte[] bytes, int position, int limit)
    {
        int length=limit-position;
        if (length<9)
            return null;

        if (bytes[position+4]=='/' && bytes[position+6]=='.' && Character.isWhitespace((char)bytes[position+8]) &&
            ((bytes[position]=='H' &&  bytes[position+1]=='T' && bytes[position+2]=='T' && bytes[position+3]=='P') ||
             (bytes[position]=='h' &&  bytes[position+1]=='t' && bytes[position+2]=='t' && bytes[position+3]=='p')))
        {
            switch(bytes[position+5])
            {
                case '1':
                    switch(bytes[position+7])
                    {
                        case '0':
                            return HTTP_1_0;
                        case '1':
                            return HTTP_1_1;
                    }
                    break;
                case '2':
                    switch(bytes[position+7])
                    {
                        case '0':
                            return HTTP_2;
                    }
                    break;
            }
        }
        
        return null;
    }
