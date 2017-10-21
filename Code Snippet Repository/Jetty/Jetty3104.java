    public void sendShutdown() throws IOException
    {
        URL url = new URL(getServerUrl() + "/shutdown?token=" + _shutdownToken);
        try
        {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.getResponseCode();
            LOG.info("Shutting down " + url + ": " + connection.getResponseCode() + " " + connection.getResponseMessage());
        }
        catch (SocketException e)
        {
            LOG.debug("Not running");
            // Okay - the server is not running
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
