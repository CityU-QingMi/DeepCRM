    public void setTrustStorePassword(String password)
    {
        if (password == null)
        {
            if (_trustStoreResource != null && !_trustStoreResource.equals(_keyStoreResource))
                _trustStorePassword = getPassword(PASSWORD_PROPERTY);
            else
                _trustStorePassword = null;
        }
        else
        {
            _trustStorePassword = newPassword(password);
        }
    }
