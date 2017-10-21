        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            if (name.endsWith("package-info")) {
                info = new PackageInfo(javaName(name), classFinder);
            } else {
                ClassInfo classInfo = new ClassInfo(javaName(name), javaName(superName), classFinder);

                for (String interfce : interfaces) {
                    classInfo.getInterfaces().add(javaName(interfce));
                }
                info = classInfo;
                classInfos.put(classInfo.getName(), classInfo);

                if (extractBaseInterfaces)
                    extractSuperInterfaces(classInfo);
            }
        }
