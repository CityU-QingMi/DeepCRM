    @Override
    public void onPathWatchEvent(PathWatchEvent event)
    {
        StringBuilder msg = new StringBuilder();
        msg.append("onPathWatchEvent: [");
        msg.append(event.getType());
        msg.append("] ");
        msg.append(event.getPath());
        msg.append(" (count=").append(event.getCount()).append(")");
        if (Files.isRegularFile(event.getPath()))
        {
            try
            {
                String fsize = String.format(" (filesize=%,d)",Files.size(event.getPath()));
                msg.append(fsize);
            }
            catch (IOException e)
            {
                LOG.warn("Unable to get filesize",e);
            }
        }
        LOG.info("{}",msg.toString());
    }
