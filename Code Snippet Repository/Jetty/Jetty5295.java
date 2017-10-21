    public void setKeyManagerPassword(String password)
    {
        if (password == null)
        {
            if (System.getProperty(KEYPASSWORD_PROPERTY) != null)
                _keyManagerPassword = getPassword(KEYPASSWORD_PROPERTY);
            else
                _keyManagerPassword = null;
        }
        else
        {
            _keyManagerPassword = newPassword(password);
        }
    }
