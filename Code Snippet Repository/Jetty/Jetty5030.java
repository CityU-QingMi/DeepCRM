    @Deprecated
    public static byte[] sidStringToBytes( String sidString )
    {
        String[] sidTokens = sidString.split("-");
        
        int subAuthorityCount = sidTokens.length - 3; // S-Rev-IdAuth-
        
        int byteCount = 0;
        byte[] sidBytes = new byte[1 + 1 + 6 + (4 * subAuthorityCount)];
        
        // the revision byte
        sidBytes[byteCount++] = (byte)Integer.parseInt(sidTokens[1]);

        // the # of sub authorities byte
        sidBytes[byteCount++] = (byte)subAuthorityCount;

        // the certAuthority
        String hexStr = Long.toHexString(Long.parseLong(sidTokens[2]));
        
        while( hexStr.length() < 12) // pad to 12 characters
        {
            hexStr = "0" + hexStr;
        }

        // place the certAuthority 6 bytes
        for ( int i = 0 ; i < hexStr.length(); i = i + 2)
        {
            sidBytes[byteCount++] = (byte)Integer.parseInt(hexStr.substring(i, i + 2),16);
        }
                
        
        for ( int i = 3; i < sidTokens.length ; ++i)
        {
            hexStr = Long.toHexString(Long.parseLong(sidTokens[i]));
            
            while( hexStr.length() < 8) // pad to 8 characters
            {
                hexStr = "0" + hexStr;
            }     
            
            // place the inverted sub authorities, 4 bytes each
            for ( int j = hexStr.length(); j > 0; j = j - 2)
            {          
                sidBytes[byteCount++] = (byte)Integer.parseInt(hexStr.substring(j-2, j),16);
            }
        }
      
        return sidBytes;
    }
