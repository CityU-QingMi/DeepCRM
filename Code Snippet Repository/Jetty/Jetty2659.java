    public void setChecked(boolean checked)
    {
        this._checked = checked;
        if (!checked)
        {
            _forbidden=false;
            _roles.clear();
            _isAnyRole=false;
            _isAnyAuth=false;
        }
    }
