    public static void toHex(int value,Appendable buf) throws IOException
    {
        int d=0xf&((0xF0000000&value)>>28);
        buf.append((char)((d>9?('A'-10):'0')+d));
        d=0xf&((0x0F000000&value)>>24);
        buf.append((char)((d>9?('A'-10):'0')+d));
        d=0xf&((0x00F00000&value)>>20);
        buf.append((char)((d>9?('A'-10):'0')+d));
        d=0xf&((0x000F0000&value)>>16);
        buf.append((char)((d>9?('A'-10):'0')+d));
        d=0xf&((0x0000F000&value)>>12);
        buf.append((char)((d>9?('A'-10):'0')+d));
        d=0xf&((0x00000F00&value)>>8);
        buf.append((char)((d>9?('A'-10):'0')+d));
        d=0xf&((0x000000F0&value)>>4);
        buf.append((char)((d>9?('A'-10):'0')+d));
        d=0xf&value;
        buf.append((char)((d>9?('A'-10):'0')+d));
    
        Integer.toString(0,36);
    }
