    @Override
    public int hashCode()
    {
        int hashCode = ( type == null ) ? 0 : type.hashCode();

        if ( this.getModel().getProvides() != null )
        {
            hashCode = 31 * hashCode + this.getModel().getProvides().hashCode();
        }
        return hashCode;
    }
