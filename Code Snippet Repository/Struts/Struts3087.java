    public static ProtectedFunctionMapper getInstance() {
        ProtectedFunctionMapper funcMapper;
        if (SecurityUtil.isPackageProtectionEnabled()) {
            funcMapper = (ProtectedFunctionMapper) AccessController
                    .doPrivileged(new PrivilegedAction() {
                        public Object run() {
                            return new ProtectedFunctionMapper();
                        }
                    });
        } else {
            funcMapper = new ProtectedFunctionMapper();
        }
        funcMapper.fnmap = new java.util.HashMap();
        return funcMapper;
    }
