    public void setKeyStorePassword(String password)
    {
        if (password == null)
        {
            if (_keyStoreResource != null)
                _keyStorePassword = getPassword(PASSWORD_PROPERTY);
            else
                _keyStorePassword = null;
        }
        else
        {
            _keyStorePassword = newPassword(password);
        }
    }
