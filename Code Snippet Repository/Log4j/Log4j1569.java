        @Override
        public void run() {
            for (final Map.Entry<File, FileMonitor> entry : watchers.entrySet()) {
                final File file = entry.getKey();
                final FileMonitor fileMonitor = entry.getValue();
                final long lastModfied = file.lastModified();
                if (fileModified(fileMonitor, lastModfied)) {
                    logger.info("File {} was modified on {}, previous modification was {}", file, lastModfied, fileMonitor.lastModifiedMillis);
                    fileMonitor.lastModifiedMillis = lastModfied;
                    fileMonitor.fileWatcher.fileModified(file);
                }
            }
        }
