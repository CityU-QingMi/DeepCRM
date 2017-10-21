    public void configureScanner() throws MojoExecutionException
    {
        scanner.watch(project.getFile().toPath());
        scanner.watch(war.toPath());

        scanner.addListener(new PathWatcher.EventListListener()
        {

            @Override
            public void onPathWatchEvents(List<PathWatchEvent> events)
            {
                try
                {
                    boolean reconfigure = false;
                    for (PathWatchEvent e:events)
                    {
                        if (e.getPath().equals(project.getFile().toPath()))
                        {
                            reconfigure = true;
                            break;
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
