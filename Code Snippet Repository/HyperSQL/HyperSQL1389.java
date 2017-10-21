        protected Long readInteger(TarHeaderField field)
                throws TarMalformatException {

            String s = readString(field);

            if (s == null) {
                return null;
            }

            try {
                return Long.valueOf(s, 8);
            } catch (NumberFormatException nfe) {
                throw new TarMalformatException(
                        RB.bad_numeric_header_value.getString(
                        field.toString(), nfe.toString()));
            }
        }
