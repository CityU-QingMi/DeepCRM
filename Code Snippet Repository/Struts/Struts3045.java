    public static String decode(String encoded) {
        // speedily leave if we're not needed
        if (encoded == null) return null;
        if (encoded.indexOf('%') == -1 && encoded.indexOf('+') == -1)
            return encoded;

        //allocate the buffer - use byte[] to avoid calls to new.
        byte holdbuffer[] = new byte[encoded.length()];

        char holdchar;
        int bufcount = 0;

        for (int count = 0; count < encoded.length(); count++) {
            char cur = encoded.charAt(count);
            if (cur == '%') {
                holdbuffer[bufcount++] =
                        (byte) Integer.parseInt(encoded.substring(count + 1, count + 3), 16);
                if (count + 2 >= encoded.length())
                    count = encoded.length();
                else
                    count += 2;
            } else if (cur == '+') {
                holdbuffer[bufcount++] = (byte) ' ';
            } else {
                holdbuffer[bufcount++] = (byte) cur;
            }
        }
        // REVISIT -- remedy for Deprecated warning.
        //return new String(holdbuffer,0,0,bufcount);
        return new String(holdbuffer, 0, bufcount);
    }
