    public static long copy(final Reader reader, final Writer writer,
                            final long amount,
                            final int bufferSize) throws IOException {

        //
        int maxCharsToRead = (int) Math.min(bufferSize, amount);

        //
        final char[] buffer = new char[maxCharsToRead];

        //
        long charsCopied = 0;
        int  charsRead;

        while ((charsCopied < amount)
                && -1 != (charsRead = reader.read(buffer, 0,
                    maxCharsToRead))) {

            //
            writer.write(buffer, 0, charsRead);

            if (charsRead > Long.MAX_VALUE - charsCopied) {

                // edge case...
                // extremely unlikely but included for 'correctness'
                charsCopied = Long.MAX_VALUE;
            } else {
                charsCopied += charsRead;
            }

            if (charsCopied >= amount) {
                return charsCopied;
            }

            maxCharsToRead = (int) Math.min(bufferSize, amount - charsCopied);
        }

        return charsCopied;
    }
