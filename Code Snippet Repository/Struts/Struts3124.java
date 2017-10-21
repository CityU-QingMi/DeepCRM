    public int read(char ch[], int offset, int length) throws IOException {
        if (length > fBuffer.length) {
            length = fBuffer.length;
        }
        int count = fInputStream.read(fBuffer, 0, length);
        for (int i = 0; i < count; i++) {
            int b0 = (0xff & fBuffer[i]); // Convert to unsigned
            if (b0 > 0x80) {
                throw new IOException(Localizer.getMessage("jsp.error.xml.invalidASCII",
							   Integer.toString(b0)));
            }
            ch[offset + i] = (char)b0;
        }
        return count;
    } // read(char[],int,int)
