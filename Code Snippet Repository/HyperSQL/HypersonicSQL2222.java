    public void addRecord(String key,
                          String value)
                          throws TarMalformatException, IOException {

        if (key == null || value == null || key.length() < 1
                || value.length() < 1) {
            throw new TarMalformatException(RB.zero_write.getString());
        }

        int lenWithoutIlen = key.length() + value.length() + 3;

        // "Ilen" means Initial Length field.  +3 = SPACE + = + \n
        int lenW = 0;    // lenW = Length With initial-length-field

        if (lenWithoutIlen < 8) {
            lenW = lenWithoutIlen + 1;    // Takes just 1 char to report total
        } else if (lenWithoutIlen < 97) {
            lenW = lenWithoutIlen + 2;    // Takes 2 chars to report this total
        } else if (lenWithoutIlen < 996) {
            lenW = lenWithoutIlen + 3;    // Takes 3...
        } else if (lenWithoutIlen < 9995) {
            lenW = lenWithoutIlen + 4;    // ditto
        } else if (lenWithoutIlen < 99994) {
            lenW = lenWithoutIlen + 5;
        } else {
            throw new TarMalformatException(RB.pif_toobig.getString(99991));
        }

        writer.write(Integer.toString(lenW));
        writer.write(' ');
        writer.write(key);
        writer.write('=');
        writer.write(value);
        writer.write('\n');
        writer.flush();    // Does this do anything with a BAOS?
    }
