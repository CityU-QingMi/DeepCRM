    public static long copy(final InputStream inputStream,
                            final OutputStream outputStream,
                            final long amount,
                            final int bufferSize) throws IOException {

        //
        int maxBytesToRead = (int) Math.min(bufferSize, amount);

        //
        final byte[] buffer = new byte[maxBytesToRead];

        //
        long bytesCopied = 0;
        int  bytesRead;

        while ((bytesCopied < amount)
                && -1 != (bytesRead = inputStream.read(buffer, 0,
                    maxBytesToRead))) {

            //
            outputStream.write(buffer, 0, bytesRead);

            if (bytesRead > Long.MAX_VALUE - bytesCopied) {

                // edge case...
                // extremely unlikely but included for 'correctness'
                bytesCopied = Long.MAX_VALUE;
            } else {
                bytesCopied += bytesRead;
            }

            if (bytesCopied >= amount) {
                return bytesCopied;
            }

            maxBytesToRead = (int) Math.min(bufferSize, amount - bytesCopied);
        }

        return bytesCopied;
    }
