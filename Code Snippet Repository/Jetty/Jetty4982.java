        public PathWatchEvent(Path path, WatchEvent<Path> event, Config config)
        {
            this.path = path;
            if (event.kind() == ENTRY_CREATE)
            {
                this.type = PathWatchEventType.ADDED;
            }
            else if (event.kind() == ENTRY_DELETE)
            {
                this.type = PathWatchEventType.DELETED;
            }
            else if (event.kind() == ENTRY_MODIFY)
            {
                this.type = PathWatchEventType.MODIFIED;
            }
            else
            {
                this.type = PathWatchEventType.UNKNOWN;
            }
            this.config = config;
            checked = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            check();
        }
