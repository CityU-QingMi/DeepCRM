    private ThreadPool makeIncompleteThreadPool() throws InstantiationException, IllegalAccessException {
        String name = "IncompleteThreadPool";
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, name, null, "java/lang/Object", new String[] { "org/quartz/spi/ThreadPool" });

        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        cw.visitEnd();

        return (ThreadPool) new ClassLoader() {
            Class<?> defineClass(String clname, byte[] b) {
                return defineClass(clname, b, 0, b.length);
            }
        }.defineClass(name, cw.toByteArray()).newInstance();
    }
