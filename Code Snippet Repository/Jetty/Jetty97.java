        public MyMethodVisitor(final Set<? extends Handler> handlers,
                               final ClassInfo classInfo,
                               final int access,
                               final String name,
                               final String methodDesc,
                               final String signature,
                               final String[] exceptions)
        {
            super(ASM_OPCODE_VERSION);
            _handlers = handlers;
            _mi = new MethodInfo(classInfo, name, access, methodDesc,signature, exceptions);
        }
