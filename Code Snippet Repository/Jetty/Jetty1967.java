    public void configureScanner ()
    throws MojoExecutionException
    {
        try
        {
            gatherScannables();
        }
        catch (Exception e)
        {
            throw new MojoExecutionException("Error forming scan list", e);
        }

        scanner.addListener(new PathWatcher.EventListListener()
        {

            @Override
            public void onPathWatchEvents(List<PathWatchEvent> events)
            {
                try
                {
                    boolean reconfigure = false;
                    if (events != null)
                    {
                        for (PathWatchEvent e:events)
                        {
                            if (e.getPath().equals(project.getFile().toPath()))
                            {
                                reconfigure = true;
                                break;
                            }
                        }
                    }

                    restartWebApp(reconfigure);
                }
                catch (Exception e)
                {
                    getLog().error("Error reconfiguring/restarting webapp after change in watched files",e);
                }
            }
        });
    }
