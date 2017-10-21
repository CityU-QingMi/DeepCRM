    private long getLength() throws IOException {

        int count = 0;

        resetStream();

        while (true) {
            if (file.read() < 0) {
                break;
            }

            count++;
        }

        return count;
    }
