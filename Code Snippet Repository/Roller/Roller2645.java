    private long getDirSize(File dir, boolean recurse) {

        long size = 0;

        if (dir.exists() && dir.isDirectory() && dir.canRead()) {
            long dirSize = 0l;
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isDirectory()) {
                        dirSize += file.length();
                    } else if (recurse) {
                        // count a subdirectory
                        dirSize += getDirSize(file, recurse);
                    }
                }
            }
            size += dirSize;
        }

        return size;
    }
