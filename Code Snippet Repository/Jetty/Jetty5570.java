        @Override
        public void onPathWatchEvent(PathWatchEvent event)
        {
            synchronized (events)
            {
                Path relativePath = this.baseDir.relativize(event.getPath());
                String key = relativePath.toString().replace(File.separatorChar,'/');

                List<PathWatchEventType> types = this.events.get(key);
                if (types == null)
                {
                    types = new ArrayList<>();
                }
                types.add(event.getType());
                this.events.put(key,types);
                LOG.debug("Captured Event: {} | {}",event.getType(),key);
            }
            //if triggered by path
            if (triggerPath != null)
            {

                if (triggerPath.equals(event.getPath()) && (event.getType() == triggerType))
                {
                    LOG.debug("Encountered finish trigger: {} on {}",event.getType(),event.getPath());
                    finishedLatch.countDown();
                }
            }
            else if (finishedLatch != null)
            {
                finishedLatch.countDown();
            }
        }
