        private void extractSuperInterfaces(ClassInfo classInfo) {
            String superType = classInfo.getSuperType();

            if (superType != null) {
                ClassInfo base = classInfos.get(superType);

                if (base == null) {
                    //try to load base
                    String resource = superType.replace('.', '/') + ".class";
                    readClassDef(resource);
                    base = classInfos.get(superType);
                }

                if (base != null) {
                    List<String> interfaces = classInfo.getSuperInterfaces();
                    interfaces.addAll(base.getSuperInterfaces());
                    interfaces.addAll(base.getInterfaces());
                }
            }
        }
