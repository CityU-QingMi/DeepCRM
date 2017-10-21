    public long skip(long n) throws IOException {
        // charWidth will represent the number of bits to move
        // n leftward to get num of bytes to skip, and then move the result rightward
        // to get num of chars effectively skipped.
        // The trick with &'ing, as with elsewhere in this dcode, is
        // intended to avoid an expensive use of / that might not be optimized
        // away.
        int charWidth = (fEncoding >=4)?2:1;
        long bytesSkipped = fInputStream.skip(n<<charWidth);
        if((bytesSkipped & (charWidth | 1)) == 0) return bytesSkipped >> charWidth;
        return (bytesSkipped >> charWidth) + 1;
    } // skip(long):long
