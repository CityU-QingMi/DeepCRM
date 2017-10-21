    public static String colonNotation(byte[] uba) {

        // TODO:  handle odd byte lengths.
        if ((uba.length / 2) * 2 != uba.length) {
            throw new RuntimeException(
                "At this time .colonNotation only handles even byte quantities");
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < uba.length; i += 2) {
            if (i > 0) {
                sb.append(':');
            }

            sb.append(Integer.toHexString((uba[i] & 0xff) * 256
                                          + (uba[i + 1] & 0xff)));
        }

        return sb.toString();
    }
