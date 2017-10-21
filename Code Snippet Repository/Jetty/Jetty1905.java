    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        buf.append(this.getClass().getName()).append('@').append(Integer.toHexString(this.hashCode()));
        buf.append("[name=").append(this._name);
        buf.append(",parent=");
        if (this._parent != null)
        {
            buf.append(this._parent.getClass().getName()).append('@').append(Integer.toHexString(this._parent.hashCode()));
        }
        buf.append(",bindings");
        if (this._bindings == null)
        {
            buf.append("=<null>");
        }
        else
        {
            buf.append(".size=").append(this._bindings.size());
        }
        buf.append(']');
        return buf.toString();
    }
