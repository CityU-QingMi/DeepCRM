    public long write(InputStream inputStream,
                      long length) throws IOException {

        byte[] data       = new byte[1024];
        long   totalCount = 0;

        while (true) {
            long count = length - totalCount;

            if (count > data.length) {
                count = data.length;
            }

            count = inputStream.read(data, 0, (int) count);

            if (count < 1) {
                break;
            }

            write(data, 0, (int) count);

            totalCount += count;
        }

        return totalCount;
    }
