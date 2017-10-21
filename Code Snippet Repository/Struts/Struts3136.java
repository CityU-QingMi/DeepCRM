    public int read() throws IOException { 
        int b0 = fInputStream.read() & 0xff;
        if (b0 == 0xff)
            return -1;
        int b1 = fInputStream.read() & 0xff;
        if (b1 == 0xff)
            return -1;
        if(fEncoding >=4) {
            int b2 = fInputStream.read() & 0xff;
            if (b2 == 0xff)
                return -1;
            int b3 = fInputStream.read() & 0xff;
            if (b3 == 0xff)
                return -1;
            if (log.isDebugEnabled())
                log.debug("b0 is " + (b0 & 0xff) + " b1 " + (b1 & 0xff) + " b2 " + (b2 & 0xff) + " b3 " + (b3 & 0xff));
            if (fEncoding == UCS4BE)
                return (b0<<24)+(b1<<16)+(b2<<8)+b3;
            else
                return (b3<<24)+(b2<<16)+(b1<<8)+b0;
        } else { // UCS-2
            if (fEncoding == UCS2BE)
                return (b0<<8)+b1;
            else
                return (b1<<8)+b0;
        }
    } // read():int
