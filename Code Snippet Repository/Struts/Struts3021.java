    public void releasePageContext(PageContext pc) {
        if( pc == null )
            return;
        if( Constants.IS_SECURITY_ENABLED ) {
            PrivilegedReleasePageContext dp = new PrivilegedReleasePageContext(
                    (JspFactoryImpl)this,pc);
            AccessController.doPrivileged(dp);
        } else {
            internalReleasePageContext(pc);
        }
    }
