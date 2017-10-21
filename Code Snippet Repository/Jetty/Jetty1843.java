    private void stopRegistry()
    {
        if (_registry != null)
        {
            try
            {
                UnicastRemoteObject.unexportObject(_registry, true);
            }
            catch (Exception ex)
            {
                LOG.ignore(ex);
            }
            finally
            {
                _registry = null;
            }
        }
    }
