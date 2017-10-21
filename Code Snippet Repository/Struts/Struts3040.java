    public static String URLEncode(String s, String enc) {

        if (s == null) {
            return "null";
        }

        if (enc == null) {
            enc = "ISO-8859-1";    // The default request encoding
        }

        StringBuffer out = new StringBuffer(s.length());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(buf, enc);
        } catch (java.io.UnsupportedEncodingException ex) {
            // Use the default encoding?
            writer = new OutputStreamWriter(buf);
        }

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (c == ' ') {
                out.append('+');
            } else if (isSafeChar(c)) {
                out.append((char) c);
            } else {
                // convert to external encoding before hex conversion
                try {
                    writer.write(c);
                    writer.flush();
                } catch (IOException e) {
                    buf.reset();
                    continue;
                }
                byte[] ba = buf.toByteArray();
                for (int j = 0; j < ba.length; j++) {
                    out.append('%');
                    // Converting each byte in the buffer
                    out.append(Character.forDigit((ba[j] >> 4) & 0xf, 16));
                    out.append(Character.forDigit(ba[j] & 0xf, 16));
                }
                buf.reset();
            }
        }
        return out.toString();
    }
