        protected String readString(TarHeaderField field) throws TarMalformatException {

            int start = field.getStart();
            int stop  = field.getStop();
            int termIndex = TarEntryHeader.indexOf(rawHeader, (byte) 0, start,
                                                   stop);

            switch (termIndex) {

                case 0 :
                    return null;

                case -1 :
                    termIndex = stop - start;
                    break;

                default:
                    break;
            }

            try {
                return new String(rawHeader, start, termIndex);
            } catch (Throwable t) {

                // Java API does not specify behavior if decoding fails.
                throw new TarMalformatException(
                        RB.bad_header_value.getString(field.toString()));
            }
        }
