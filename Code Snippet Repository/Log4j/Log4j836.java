    @Override
    public int compare(final PathWithAttributes path1, final PathWithAttributes path2) {
        final long lastModified1 = path1.getAttributes().lastModifiedTime().toMillis();
        final long lastModified2 = path2.getAttributes().lastModifiedTime().toMillis();
        int result = Long.signum(lastModified2 - lastModified1);
        if (result == 0) { // if same time compare paths lexicographically
            try {
                // assuming paths contain counters and dates, use reverse lexicographical order:
                // 20151129 before 20151128, path-2.log before path-1.log
                result = path2.getPath().compareTo(path1.getPath());
            } catch (final ClassCastException ex) {
                result = path2.getPath().toString().compareTo(path1.getPath().toString());
            }
        }
        return multiplier * result;
    }
