    private void writeState(String action, LifeCycle lifecycle)
    {
        try (Writer out = new FileWriter(_filename,true))
        {
            out.append(action).append(" ").append(lifecycle.toString()).append("\n");
        }
        catch(Exception e)
        {
            LOG.warn(e);
        }
    }
