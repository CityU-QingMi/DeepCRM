        static protected void writeField(TarHeaderField field, String newValue,
                                         byte[] target)
                throws TarMalformatException {
            int    start = field.getStart();
            int    stop  = field.getStop();
            byte[] ba;

            try {
                ba = newValue.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            if (ba.length > stop - start) {
                throw new TarMalformatException(
                    RB.tar_field_toobig.getString(field.toString(), newValue));
            }

            for (int i = 0; i < ba.length; i++) {
                target[start + i] = ba[i];
            }
        }
