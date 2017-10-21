    public void removeProtoTypeFiles(String classFileName) {
        Iterator iter = tempVector.iterator();
        while (iter.hasNext()) {
            Compiler c = (Compiler) iter.next();
            if (classFileName == null) {
                c.removeGeneratedClassFiles();
            } else if (classFileName.equals(c.getCompilationContext()
                    .getClassFileName())) {
                c.removeGeneratedClassFiles();
                tempVector.remove(c);
                return;
            }
        }
    }
