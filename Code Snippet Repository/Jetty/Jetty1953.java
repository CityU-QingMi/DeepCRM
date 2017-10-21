    private void restartWebApp()
    {
        try
        {
            mojo.restartWebApp(false);
            // Clear input buffer to discard anything entered on the console
            // while the application was being restarted.
            clearInputBuffer();
        }
        catch (Exception e)
        {
            mojo.getLog().error(
                            "Error reconfiguring/restarting webapp after a new line on the console",
                            e);
        }
    }
