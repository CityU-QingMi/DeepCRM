    public void setForbidden(boolean forbidden)
    {
        this._forbidden = forbidden;
        if (forbidden)
        {
            _checked = true;
            _userDataConstraint = null;
            _isAnyRole=false;
            _isAnyAuth=false;
            _roles.clear();
        }
    }
