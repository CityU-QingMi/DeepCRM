    @Deprecated
    public static String sidBytesToString(byte[] sidBytes)
    {
        StringBuilder sidString = new StringBuilder();
        
        // Identify this as a SID
        sidString.append("S-");
        
        // Add SID revision level (expect 1 but may change someday)
        sidString.append(Byte.toString(sidBytes[0])).append('-');
        
        StringBuilder tmpBuilder = new StringBuilder();
        
        // crunch the six bytes of issuing authority value
        for (int i = 2; i <= 7; ++i)
        {
            tmpBuilder.append(Integer.toHexString(sidBytes[i] & 0xFF));
        }
        
        sidString.append(Long.parseLong(tmpBuilder.toString(), 16)); // '-' is in the subauth loop
   
        // the number of subAuthorities we need to attach
        int subAuthorityCount = sidBytes[1];

        // attach each of the subAuthorities
        for (int i = 0; i < subAuthorityCount; ++i)
        {
            int offset = i * 4;
            tmpBuilder.setLength(0);
            // these need to be zero padded hex and little endian
            tmpBuilder.append(String.format("%02X%02X%02X%02X", 
                    (sidBytes[11 + offset] & 0xFF),
                    (sidBytes[10 + offset] & 0xFF),
                    (sidBytes[9 + offset] & 0xFF),
                    (sidBytes[8 + offset] & 0xFF)));  
            sidString.append('-').append(Long.parseLong(tmpBuilder.toString(), 16));
        }
        
        return sidString.toString();
    }
