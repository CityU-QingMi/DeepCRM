    @Override
    public void sendObject(Object data) throws IOException, EncodeException
    {
        // TODO avoid the use of a Future
        Future<Void> fut = sendObjectViaFuture(data);
        try
        {
            fut.get(); // block till done
        }
        catch (ExecutionException e)
        {
            throw new IOException("Failed to write object",e.getCause());
        }
        catch (InterruptedException e)
        {
            throw new IOException("Failed to write object",e);
        }
    }
