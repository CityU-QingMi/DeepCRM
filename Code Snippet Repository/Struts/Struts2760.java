    private void genCommonPostamble() {
        // Append any methods that were generated in the buffer.
        for (int i = 0; i < methodsBuffered.size(); i++) {
            GenBuffer methodBuffer = (GenBuffer) methodsBuffered.get(i);
            methodBuffer.adjustJavaLines(out.getJavaLine() - 1);
            out.printMultiLn(methodBuffer.toString());
        }

        // Append the helper class
        if (fragmentHelperClass.isUsed()) {
            fragmentHelperClass.generatePostamble();
            fragmentHelperClass.adjustJavaLines(out.getJavaLine() - 1);
            out.printMultiLn(fragmentHelperClass.toString());
        }

        // Append char array declarations
        if (charArrayBuffer != null) {
            out.printMultiLn(charArrayBuffer.toString());
        }

        // Close the class definition
        out.popIndent();
        out.printil("}");
    }
