    static protected int generateBufferBlockValue(File[] files) {

        long maxFileSize = 0;

        for (int i = 0; i < files.length; i++) {
            if (files[i] == null) {
                continue;
            }

            if (files[i].length() > maxFileSize) {
                maxFileSize = files[i].length();
            }
        }

        int idealBlocks = (int) (maxFileSize / (10L * 512L));

        // I.e., 1/10 of the file, in units of 512 byte blocks.
        // It's fine that operations will truncate down instead of round.
        if (idealBlocks < 1) {
            return 1;
        }

        if (idealBlocks > 40 * 1024) {
            return 40 * 1024;
        }

        return idealBlocks;
    }
